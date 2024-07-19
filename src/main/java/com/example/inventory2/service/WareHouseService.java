package com.example.inventory2.service;

import com.example.inventory2.dto.WarehouseDTO;
import com.example.inventory2.entity.Warehouse;
import java.util.List;

public interface WarehouseService {
  List<WarehouseDTO> getAllWarehouses();

  default WarehouseDTO entityToDto(Warehouse warehouse) {
    if (warehouse == null) {
      return null;
    }
    return WarehouseDTO
      .builder()
      .warehouseId(warehouse.getWarehouseId())
      .warehouseName(warehouse.getWarehouseName())
      .build();
  }

  default Warehouse dtoToEntity(WarehouseDTO warehouseDTO) {
    if (warehouseDTO == null) {
      return null;
    }
    return Warehouse
      .builder()
      .warehouseId(warehouseDTO.getWarehouseId())
      .warehouseName(warehouseDTO.getWarehouseName())
      .build();
  }
}
