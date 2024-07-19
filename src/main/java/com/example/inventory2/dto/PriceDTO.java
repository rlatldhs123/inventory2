package com.example.inventory2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {

  private Long priceId;
  private Long purchaseCost;
  private Long supplyPrice;
  private Long deliveryPrice;
  private Long naverPrice;
  private Long duty;
  private Long shippingCost;

  // ProductDTO를 매개변수로 받는 생성자 추가
  public PriceDTO(ProductDTO productDTO) {
    this.supplyPrice = productDTO.getSupplyPrice();
    this.deliveryPrice = productDTO.getDeliveryPrice();
    this.naverPrice = productDTO.getNaverPrice();
    this.duty = productDTO.getDuty();
    this.shippingCost = productDTO.getShippingCost();
  }
}
