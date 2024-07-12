package com.example.inventory2.service;

import com.example.inventory2.dto.InventoryDTO;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.entity.Inventory;
import com.example.inventory2.entity.Product;
import java.util.List;

public interface InventoryService {
  default InventoryDTO entityToDto(Inventory inventory) {
    if (inventory == null) {
      return null;
    }

    return InventoryDTO
      .builder()
      .inventoryId(inventory.getInventoryId())
      .inventoryStatus(inventory.getInventoryStatus())
      .quantity(inventory.getQuantity())
      // .warehouseId(inventory.getWarehouse().getWarehouseId())
      .build();
  }
}
