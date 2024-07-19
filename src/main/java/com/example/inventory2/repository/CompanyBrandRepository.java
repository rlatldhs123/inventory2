package com.example.inventory2.repository;

import com.example.inventory2.entity.Brand;
import com.example.inventory2.entity.Company;
import com.example.inventory2.entity.CompanyBrand;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyBrandRepository
  extends JpaRepository<CompanyBrand, Long> {
  // 컴퍼니와 컴퍼니 네임이 일치하는 pk  찾아내기
  @Query(
    "SELECT cb FROM CompanyBrand cb " + // <<<<<<<<<<
    "JOIN cb.company c " + // <<<<<<<<<<
    "JOIN cb.brand b " + // <<<<<<<<<<
    "WHERE c.companyName = :companyName " + // <<<<<<<<<<
    "AND c.companyCode = :companyCode " + // <<<<<<<<<<
    "AND b.brandName = :brandName " + // <<<<<<<<<<
    "AND b.brandCode = :brandCode"
  ) // <<<<<<<<<<
  CompanyBrand findByCompanyAndBrand(
    @Param("companyName") String companyName,
    @Param("companyCode") String companyCode,
    @Param("brandName") String brandName,
    @Param("brandCode") String brandCode
  ); // <<<<<<<<<<

  // Company와 Brand로 CompanyBrand 조회
  List<CompanyBrand> findByCompanyAndBrand(Company company, Brand brand);
}
