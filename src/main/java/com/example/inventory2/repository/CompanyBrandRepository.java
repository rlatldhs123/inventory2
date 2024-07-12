package com.example.inventory2.repository;

import com.example.inventory2.entity.CompanyBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyBrandRepository
  extends JpaRepository<CompanyBrand, Long> {}
