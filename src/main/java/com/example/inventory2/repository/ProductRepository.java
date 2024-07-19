package com.example.inventory2.repository;

import com.example.inventory2.dto.ProductInventoryDTO;
import com.example.inventory2.dto.view.ProductListView;
import com.example.inventory2.entity.Product;
import com.example.inventory2.repository.total.ProductCompanyBrandPriceRepository;
import com.example.inventory2.status.QuantitySatatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ProductRepository
    extends JpaRepository<Product, Long>, ProductCompanyBrandPriceRepository {
  // 상품 코드 기준 검색
  List<Product> findByProductCodeContaining(String productCode);

  // 상품 이름 기준 검색
  List<Product> findByProductNameContaining(String productName);

  // 상품 구성품 기준 검색
  List<Product> findByComponentsContaining(String components);
  // 상품 재고상태 기준 별 검색

  // 재고 총 수량을 뽑는 것은 어떻게 하면 될까
  // List<Product> findByQuantityStatus(String quantityStatus);

  // 메소드 정상 작동 확인
  Page<Product> findByQuantityStatus(
      QuantitySatatus quantityStatus,
      Pageable pageable);

  // 재고 이동 필수 메소드
  Product findByProductCode(String productCode);

  // 인벤토리 기능 해당 위치에 작성 함 (기간 이슈)
  // 코드 설명 상품의 총 수량과 @param 어노테이션으로
  // 상품명과 상품 코드 기준으로 검색이 가능하게 함

  // @Query(
  // "SELECT new com.example.inventory2.dto.ProductInventoryDTO(p.productCode,
  // p.productName, p.opt, SUM(i.quantity), " +
  // "pr.purchaseCost, pr.supplyPrice, pr.deliveryPrice, pr.naverPrice, pr.duty,
  // pr.shippingCost) " +
  // "FROM Product p " +
  // "JOIN Inventory i ON p.productId = i.product.productId " +
  // "JOIN Price pr ON p.price.priceId = pr.priceId " +
  // "WHERE (:productCode = '' OR LOWER(p.productCode) LIKE LOWER(CONCAT('%',
  // :productCode, '%'))) " +
  // "AND (:productName = '' OR LOWER(p.productName) LIKE LOWER(CONCAT('%',
  // :productName, '%'))) " +
  // "GROUP BY p.productCode, p.productName, p.opt, pr.purchaseCost,
  // pr.supplyPrice, pr.deliveryPrice, pr.naverPrice, pr.duty, pr.shippingCost"
  // )
  // List<ProductInventoryDTO> searchProducts(
  // @Param("productCode") String productCode,
  // @Param("productName") String productName
  // );

  @Query("SELECT new com.example.inventory2.dto.ProductInventoryDTO(p.productCode, p.productName, p.opt, p.mainImage, SUM(i.quantity), "
      +
      "pr.purchaseCost, pr.supplyPrice, pr.deliveryPrice, pr.naverPrice, pr.duty, pr.shippingCost) " +
      "FROM Product p " +
      "JOIN Inventory i ON p.productId = i.product.productId " +
      "JOIN Price pr ON p.price.priceId = pr.priceId " +
      "WHERE (:productCode = '' OR LOWER(p.productCode) LIKE LOWER(CONCAT('%', :productCode, '%'))) " +
      "AND (:productName = '' OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))) " +
      "GROUP BY p.productCode, p.productName, p.opt, p.mainImage, pr.purchaseCost, pr.supplyPrice, pr.deliveryPrice, pr.naverPrice, pr.duty, pr.shippingCost")
  List<ProductInventoryDTO> searchProducts(
      @Param("productCode") String productCode,
      @Param("productName") String productName);

  // 유저가 정확한 상품코드를 입력 해야만 작동
  // 나중에 화면단 또는 백에서 발리데이션 걸기
  // 상품코드를 pk 로 인식하고 있는 사용자를 위해 만든 메소드
  @Query("SELECT p FROM Product p WHERE p.productCode = :productCode")
  Product findProductIdByProductCode(@Param("productCode") String productCode);
}
