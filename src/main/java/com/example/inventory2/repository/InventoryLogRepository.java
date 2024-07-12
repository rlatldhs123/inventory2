package com.example.inventory2.repository;

import com.example.inventory2.entity.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryLogRepository
  extends JpaRepository<InventoryLog, Long> {}
