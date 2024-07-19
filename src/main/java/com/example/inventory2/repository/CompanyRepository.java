package com.example.inventory2.repository;

import com.example.inventory2.entity.Brand;
import com.example.inventory2.entity.Company;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
  Company findByCompanyName(String companyName);

  // CompanyCode로 회사 조회
  Company findByCompanyCode(String companyCode);
}
