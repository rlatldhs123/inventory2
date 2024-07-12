package com.example.inventory2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailImageDTO {

  private Long detailImageNum;
  private String detailImageUrl;
  private Long productId;
}
