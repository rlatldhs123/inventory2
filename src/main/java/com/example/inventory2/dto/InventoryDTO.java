package com.example.inventory2.dto;

import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.WarehouseName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDTO {

  private Long inventoryId;
  private int quantity;

  private InventoryStatus inventoryStatus;
  private String warehouseName;
  private String productCode;
  private String productName;
  private String opt;
  private Long productId;
}
