package com.example.inventory2.repository;

import com.example.inventory2.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {}
