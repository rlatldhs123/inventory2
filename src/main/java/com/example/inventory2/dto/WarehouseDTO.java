package com.example.inventory2.dto;

import com.example.inventory2.status.WarehouseName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {

  private Long warehouseId;
  private WarehouseName warehouseName;
}
