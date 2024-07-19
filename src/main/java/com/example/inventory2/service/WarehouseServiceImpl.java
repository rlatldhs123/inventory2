package com.example.inventory2.service;

import com.example.inventory2.dto.WarehouseDTO;
import com.example.inventory2.entity.Warehouse;
import com.example.inventory2.repository.WarehouseRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

  private final WarehouseRepository warehouseRepository;

  @Override
  public List<WarehouseDTO> getAllWarehouses() {
    List<Warehouse> warehouses = warehouseRepository.findAll();
    return warehouses
      .stream()
      .map(this::entityToDto)
      .collect(Collectors.toList());
  }
}
