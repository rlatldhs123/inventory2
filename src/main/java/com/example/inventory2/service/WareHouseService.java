package com.example.inventory2.service;

import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.dto.WarehouseDTO;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.Warehouse;
import java.util.List;

public interface WareHouseService {
  List<Object[]> getAllProductDetails();

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
}
