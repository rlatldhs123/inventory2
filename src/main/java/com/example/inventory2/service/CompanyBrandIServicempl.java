package com.example.inventory2.service;

import com.example.inventory2.dto.CompanyBrandDTO;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.repository.CompanyBrandRepository;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyBrandIServicempl implements CompanyBrandService {

  private final CompanyBrandRepository companyBrandRepository;

  @Override
  public List<CompanyBrandDTO> getCompanyBrandList() {
    List<CompanyBrand> companyBrands = companyBrandRepository.findAll();

    return companyBrands
      .stream()
      .map(this::entityToDto)
      .collect(Collectors.toList());
  }
}
