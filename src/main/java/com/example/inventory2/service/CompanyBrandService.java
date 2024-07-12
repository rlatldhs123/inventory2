package com.example.inventory2.service;

import com.example.inventory2.dto.CompanyBrandDTO;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.dto.WarehouseDTO;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.Warehouse;
import java.util.List;

public interface CompanyBrandService {
  default CompanyBrandDTO entityToDto(CompanyBrand companyBrand) {
    if (companyBrand == null) {
      return null;
    }

    return CompanyBrandDTO
      .builder()
      .companyBrandId(companyBrand.getCompanyBrandId())
      .companyName(companyBrand.getCompany().getCompanyName())
      .brandName(companyBrand.getBrand().getBrandName())
      .build();
  }
}
