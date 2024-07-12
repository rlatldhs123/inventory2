package com.example.inventory2.dto;

import com.example.inventory2.status.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

  private Long inventoryId;
  private InventoryStatus inventoryStatus;
  private int quantity;
  private Long productId;
  private Long warehouseId;
}
