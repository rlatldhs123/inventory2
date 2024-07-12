package com.example.inventory2.service;

import com.example.inventory2.dto.CompanyBrandDTO;
import com.example.inventory2.dto.CompanyDTO;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.dto.WarehouseDTO;
import com.example.inventory2.entity.Company;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.Warehouse;
import java.util.List;

public interface CompanyService {
  default CompanyDTO entityToDto(Company company) {
    if (company == null) {
      return null;
    }

    // return CompanyDTO
    //   .builder()
    //   .companyId(companyBrand.getCompanyBrandId())
    //   .companyName(companyBrand.getCompany().getCompanyName())
    //   .brandName(companyBrand.getBrand().getBrandName())
    //   .build();
    return null;
  }
}
