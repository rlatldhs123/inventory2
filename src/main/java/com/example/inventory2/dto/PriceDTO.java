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
}
