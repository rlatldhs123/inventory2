package com.example.inventory2.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MoveDTO {

  private List<InventoryDTO> invetoryList;
}
