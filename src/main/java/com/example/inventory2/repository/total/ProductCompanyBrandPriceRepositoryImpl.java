package com.example.inventory2.repository.total;

import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.QBrand;
import com.example.inventory2.entity.QCompany;
import com.example.inventory2.entity.QCompanyBrand;
import com.example.inventory2.entity.QPrice;
import com.example.inventory2.entity.QProduct;
import com.example.inventory2.status.QuantitySatatus;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import groovyjarjarantlr4.v4.parse.ANTLRParser.ruleReturns_return;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

@Log4j2
public class ProductCompanyBrandPriceRepositoryImpl
    extends QuerydslRepositorySupport
    implements ProductCompanyBrandPriceRepository {

  public ProductCompanyBrandPriceRepositoryImpl() {
    super(Product.class);

  }

  @Override
  public Page<Object[]> getProductList(
      String type,
      String keyword,
      String quantityStatusStr,
      Pageable pageable) {

    log.info("=================================");
    log.info("상품 전체 리스트 {},{},{}", type, keyword, quantityStatusStr);

    // 일단 q클래스를 나열 한다
    QProduct product = QProduct.product;
    QCompanyBrand companyBrand = QCompanyBrand.companyBrand;
    QPrice price = QPrice.price;

    JPQLQuery<Product> query = from(product);
    query.leftJoin(companyBrand).on(product.companyBrand.eq(companyBrand));
    query.leftJoin(price).on(product.price.eq(price));

    // 해당 순서대로 서비스에서 인덱싱 [] 해서 가져옴
    JPQLQuery<Tuple> tuple = query.select(product, companyBrand, price);

    // 검색 조건을 추가하기 위한 BooleanBuilder 객체를 생성

    BooleanBuilder builder = new BooleanBuilder();
    // 기본 조건을 추가: 모든 productId가 0보다 큰 조건
    builder.and(product.productId.gt(0L));

    // type과 키워드를 이용한 추가조건
    BooleanBuilder conditionBuilder = new BooleanBuilder();
    if (type.contains("productCode")) {
      conditionBuilder.or(product.productCode.containsIgnoreCase(keyword));
    }
    if (type.contains("productName")) {
      conditionBuilder.or(product.productName.contains(keyword));
    }

    // quantityStatusStr이 빈 문자열인 경우 현재 조건들만 반환
    // if (quantityStatusStr.length() == 0) return builder;

    // QuantitySatatus 변수를 초기화
    QuantitySatatus quantityStatus = null;

    // quantityStatusStr이 "전체"가 아닌 경우에만 상태 조건을 추가
    // quantityStatus 값이 null이 아닌 경우에만 조건을 추가

    if (quantityStatusStr != null && quantityStatusStr != "" && !quantityStatusStr.equals("전체")) {
      // quantityStatusStr 값에 따라 적절한 QuantitySatatus 값을 설정
      switch (quantityStatusStr) {
        case "원활":
          quantityStatus = QuantitySatatus.원활;
          break;
        case "부족":
          quantityStatus = QuantitySatatus.부족;
          break;
        case "경고":
          quantityStatus = QuantitySatatus.경고;
          break;
        case "품절":
          quantityStatus = QuantitySatatus.품절;
          break;
      }

      // quantityStatus 값이 null이 아닌 경우에만 조건을 추가

      conditionBuilder.or(product.quantityStatus.eq(quantityStatus));
    }

    builder.and(conditionBuilder);
    tuple.where(conditionBuilder);

    Sort sort = pageable.getSort();
    sort.stream().forEach(order -> {
      Order direction = order.isAscending() ? Order.ASC : Order.DESC;
      String prop = order.getProperty();

      PathBuilder<Product> orderByExpression = new PathBuilder<>(Product.class, "product");
      tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
    });

    // 페이지 처리
    tuple.offset(pageable.getOffset());
    tuple.limit(pageable.getPageSize());

    List<Tuple> result = tuple.fetch();
    long count = tuple.fetchCount();

    return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
  }

  @Override
  public List<Object[]> getProductRow(Long productId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getProductRow'");
  }

}
