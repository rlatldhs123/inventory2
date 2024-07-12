package com.example.inventory2.dto;

import com.example.inventory2.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

  private Long productId;
  private String productCode;
  private String productName;
  private String productOption;
  private String productNum;
  private String components;
  private String extraPurchaseOption;
  private String remarks;
  private String modifyContent;
  private String mainImage;
  private String onlineCase;
  private String category;
  private String manager;
  private int threshold;
  private String quantityStatus;
}
