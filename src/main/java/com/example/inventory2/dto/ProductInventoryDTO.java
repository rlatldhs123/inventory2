package com.example.inventory2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventoryDTO {

  // 해당 DTO 파일은 재고의 총 재고량
  // 매입가 총합,
  // 공급가 총합,
  // 판매가 총합
  // 을 구하기 위해 만들어짐

  private String productCode;

  private String productName;

  private String opt;

  private String mainImage; // 추가된 필드

  private Long totalQuantity;

  private Long purchaseCost;

  private Long supplyPrice;

  private Long deliveryPrice;

  private Long naverPrice;

  private Long duty;

  private Long shippingCost;
}
