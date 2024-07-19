package com.example.inventory2.repository;

import com.example.inventory2.entity.Brand;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
  // BrandCode로 브랜드 조회
  Brand findByBrandCode(String brandCode);
}
