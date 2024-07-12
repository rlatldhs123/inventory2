package com.example.inventory2.repository;

import com.example.inventory2.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {}
