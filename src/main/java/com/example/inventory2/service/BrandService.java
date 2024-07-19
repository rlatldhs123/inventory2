package com.example.inventory2.service;

import com.example.inventory2.dto.BrandDTO;
import com.example.inventory2.entity.Brand;
import java.util.List;

public interface BrandService {
  List<BrandDTO> getBrandList();

  // 브랜드 정보 저장
  void save(BrandDTO brandDTO);

  // DTO를 엔티티로 변환
  public default Brand dtoToEntity(BrandDTO dto) {
    if (dto == null) {
      return null;
    }

    Brand brand = Brand
      .builder()
      .brandId(dto.getBrandId())
      .brandName(dto.getBrandName())
      .brandCode(dto.getBrandCode())
      .build();

    return brand;
  }

  // 엔티티를 DTO로 변환
  public default BrandDTO entityToDto(Brand entity) {
    if (entity == null) {
      return null;
    }

    BrandDTO dto = BrandDTO
      .builder()
      .brandId(entity.getBrandId())
      .brandName(entity.getBrandName())
      .brandCode(entity.getBrandCode())
      .build();

    return dto;
  }
}
