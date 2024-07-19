package com.example.inventory2.service;

import com.example.inventory2.dto.CompanyDTO;
import com.example.inventory2.entity.Company;
import com.example.inventory2.repository.CompanyRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository companyRepository;

  @Override
  public List<CompanyDTO> getCompanyList() {
    List<Company> entityList = companyRepository.findAll();
    return entityList
      .stream()
      .map(this::entityToDto)
      .collect(Collectors.toList());
  }

  @Override
  public void save(CompanyDTO companyDTO) {
    Company company = dtoToEntity(companyDTO);
    companyRepository.save(company);
  }
}
