package com.example.inventory2.service;

import com.example.inventory2.dto.BrandDTO;
import com.example.inventory2.dto.CompanyDTO;
import com.example.inventory2.entity.Company;
import com.example.inventory2.entity.CompanyBrand;
import java.util.List;

public interface CompanyService {
  List<CompanyDTO> getCompanyList();

  // 회사 정보 저장
  void save(CompanyDTO companyDTO);

  // DTO를 엔티티로 변환
  default Company dtoToEntity(CompanyDTO dto) {
    if (dto == null) {
      return null;
    }

    return Company
      .builder()
      .companyId(dto.getCompanyId())
      .companyName(dto.getCompanyName())
      .companyCode(dto.getCompanyCode())
      .build();
  }

  // 엔티티를 DTO로 변환
  default CompanyDTO entityToDto(Company entity) {
    if (entity == null) {
      return null;
    }

    return CompanyDTO
      .builder()
      .companyId(entity.getCompanyId())
      .companyName(entity.getCompanyName())
      .companyCode(entity.getCompanyCode())
      .build();
  }
}
