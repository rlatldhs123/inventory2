package com.example.inventory2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

  private Long brandId;

  private String brandName;

  private String brandCode;

  // ProductDTO를 매개변수로 받는 생성자 추가
  public BrandDTO(ProductDTO productDTO) {
    this.brandName = productDTO.getBrandName();
    this.brandCode = productDTO.getBrandCode();
  }
}
