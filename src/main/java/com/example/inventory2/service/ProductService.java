package com.example.inventory2.service;

import com.example.inventory2.dto.BrandDTO;
import com.example.inventory2.dto.CompanyDTO;
import com.example.inventory2.dto.PageRequestDto;
import com.example.inventory2.dto.PageResultDto;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.dto.ProductInventoryDTO;
import com.example.inventory2.entity.Company;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Price;
import com.example.inventory2.entity.Product;
import com.example.inventory2.repository.CompanyBrandRepository;
import com.example.inventory2.repository.ProductRepository;
import com.example.inventory2.status.QuantitySatatus;
import com.querydsl.core.types.dsl.ComparableEntityPath;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  // 상품 리스트
  PageResultDto<ProductDTO, Object[]> getList(PageRequestDto requestDto);

  // 상품 하나 가져오기
  ProductDTO getProduct(String productCode);

  //

  List<ProductInventoryDTO> searchProducts(String productCode, String productName);

  // 재고 수량 상태별(경고,부족,품절,원활) 조회
  PageResultDto<ProductDTO, Product> findByQuantityStatusList(
      QuantitySatatus quantitySatatus,
      Pageable pageable);

  Long productCreate(ProductDTO productDTO);

  // 상품 생성시 컴퍼니 브랜드를 할당 시키는 로직
  CompanyBrand createCompanyBrand(ProductDTO productDTO);

  // 상품 추가

  // public default ProductDTO entityToDto(Product product) {
  // if (product == null) {
  // return null;
  // }
  // // QuantityStatus
  // // Quantitytatus

  // return ProductDTO
  // .builder()
  // .productId(product.getProductId())
  // .productCode(product.getProductCode())
  // .productName(product.getProductName())
  // .opt(product.getOpt())
  // .onlineCase(product.getOnlineCase())
  // .quantityStatus(product.getQuantityStatus())
  // .productNum(product.getProductNum())
  // .components(product.getComponents())
  // .extraPurchaseOption(product.getExtraPurchaseOption())
  // .remarks(product.getRemarks())
  // .modifyContent(product.getModifyContent())
  // .mainImage(product.getMainImage())
  // .category(product.getCategory())
  // .manager(product.getManager())
  // .threshold(product.getThreshold())
  // .threshold2(product.getThreshold2())
  // .soldOut(product.getSoldout())
  // // 가격
  // .purchaseCost(product.getPrice().getPurchaseCost())
  // .supplyPrice(product.getPrice().getSupplyPrice())
  // .deliveryPrice(product.getPrice().getDeliveryPrice())
  // .naverPrice(product.getPrice().getNaverPrice())
  // .shippingCost(product.getPrice().getShippingCost())
  // .duty(product.getPrice().getDuty())
  // // ----가격 종료
  // // 회사
  // .companyName(product.getCompanyBrand().getCompany().getCompanyName())
  // .companyCode(product.getCompanyBrand().getCompany().getCompanyCode())
  // // 브랜드
  // .brandCode(product.getCompanyBrand().getBrand().getBrandCode())
  // .brandName(product.getCompanyBrand().getBrand().getBrandName())
  // .build();
  // }

  // // 재고상태를 추가로 설정

  // public default Map<String, Object> dtoToEntity(
  // ProductDTO productDTO,
  // CompanyBrand companyBrand) {
  // if (productDTO == null) {
  // return null;
  // }

  // Map<String, Object> entityMap = new HashMap<>();

  // Product product = new Product();
  // product.setProductId(productDTO.getProductId());
  // product.setProductCode(productDTO.getProductCode());
  // product.setProductName(productDTO.getProductName());
  // product.setOpt(productDTO.getOpt());
  // product.setOnlineCase(productDTO.getOnlineCase());
  // product.setQuantityStatus(productDTO.getQuantityStatus());
  // product.setProductNum(productDTO.getProductNum());
  // product.setComponents(productDTO.getComponents());
  // product.setExtraPurchaseOption(productDTO.getExtraPurchaseOption());
  // product.setRemarks(productDTO.getRemarks());
  // product.setModifyContent(productDTO.getModifyContent());
  // product.setMainImage(productDTO.getMainImage());
  // product.setCategory(productDTO.getCategory());
  // product.setManager(productDTO.getManager());
  // product.setThreshold(productDTO.getThreshold());
  // product.setThreshold2(productDTO.getThreshold2());
  // product.setSoldout(productDTO.getSoldOut());
  // // Brand와 Company는 별도의 로직을 통해 설정

  // entityMap.put("product", product);

  // Price price = new Price();

  // price.setSupplyPrice(productDTO.getSupplyPrice());
  // price.setDeliveryPrice(productDTO.getDeliveryPrice());
  // price.setNaverPrice(productDTO.getNaverPrice());
  // price.setDuty(productDTO.getDuty());
  // price.setShippingCost(productDTO.getShippingCost());

  // entityMap.put("price", price);

  // // Company company

  // return entityMap;
  // }

  public default Map<String, Object> dtoToEntity(
      ProductDTO productDTO,
      CompanyBrand companyBrand) { // 수정: dtoToEntity 메서드가 Map<String, Object>를 반환하도록 변경
    Map<String, Object> entityMap = new HashMap<>();
    Product product = new Product();
    product.setProductId(productDTO.getProductId());
    product.setProductCode(productDTO.getProductCode());
    product.setProductName(productDTO.getProductName());
    product.setOpt(productDTO.getOpt());
    product.setOnlineCase(productDTO.getOnlineCase());
    product.setQuantityStatus(productDTO.getQuantityStatus());
    product.setProductNum(productDTO.getProductNum());
    product.setComponents(productDTO.getComponents());
    product.setExtraPurchaseOption(productDTO.getExtraPurchaseOption());
    product.setRemarks(productDTO.getRemarks());
    product.setModifyContent(productDTO.getModifyContent());
    product.setMainImage(productDTO.getMainImage());
    product.setCategory(productDTO.getCategory());
    product.setManager(productDTO.getManager());
    product.setThreshold(productDTO.getThreshold());
    product.setThreshold2(productDTO.getThreshold2());
    product.setSoldout(productDTO.getSoldOut());
    product.setCompanyBrand(companyBrand);

    entityMap.put("product", product); // 수정: Product 객체를 Map에 추가

    Price price = new Price();
    price.setPurchaseCost(productDTO.getPurchaseCost());
    price.setSupplyPrice(productDTO.getSupplyPrice());
    price.setDeliveryPrice(productDTO.getDeliveryPrice());
    price.setNaverPrice(productDTO.getNaverPrice());
    price.setDuty(productDTO.getDuty());
    price.setShippingCost(productDTO.getShippingCost());

    entityMap.put("price", price); // 수정: Price 객체를 Map에 추가
    return entityMap;
  }

  public default ProductDTO entityToDto(Product product, CompanyBrand companyBrand, Price price) {
    return ProductDTO
        .builder()
        .productId(product.getProductId())
        .productCode(product.getProductCode())
        .productName(product.getProductName())
        .opt(product.getOpt())
        .onlineCase(product.getOnlineCase())
        .quantityStatus(product.getQuantityStatus())
        .productNum(product.getProductNum())
        .components(product.getComponents())
        .extraPurchaseOption(product.getExtraPurchaseOption())
        .remarks(product.getRemarks())
        .modifyContent(product.getModifyContent())
        .mainImage(product.getMainImage())
        .category(product.getCategory())
        .manager(product.getManager())
        .threshold(product.getThreshold())
        .threshold2(product.getThreshold2())
        .soldOut(product.getSoldout())

        .supplyPrice(price.getSupplyPrice())
        .deliveryPrice(price.getDeliveryPrice())
        .naverPrice(price.getNaverPrice())
        .shippingCost(price.getShippingCost())
        .duty(price.getDuty())

        .companyName(companyBrand.getCompany().getCompanyName())
        .companyCode(companyBrand.getCompany().getCompanyCode())
        .brandCode(companyBrand.getBrand().getBrandCode())
        .brandName(companyBrand.getBrand().getBrandName())
        .build();
  }

}
