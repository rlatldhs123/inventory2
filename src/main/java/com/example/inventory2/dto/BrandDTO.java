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
}
