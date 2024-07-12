package com.example.inventory2.dto;

import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.LogType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryLogDTO {

  private Long inventoryLogId;
  private LocalDateTime inventoryStartDate;
  private LocalDateTime inventoryModifyDate;
  private InventoryStatus inventoryStatus;
  private LogType logType;
  private int quantityChange;
  private Long inventoryId;
}
