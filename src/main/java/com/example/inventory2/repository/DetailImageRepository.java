package com.example.inventory2.repository;

import com.example.inventory2.entity.DetailImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailImageRepository
  extends JpaRepository<DetailImage, Long> {}
