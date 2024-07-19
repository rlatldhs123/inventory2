package com.example.inventory2.dto;

import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.QuantitySatatus;
import com.example.inventory2.status.WarehouseName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@Builder
public class PageRequestDto {

  private int size;
  private int page;

  private String type;
  private String keyword;

  private String quantityStatusStr;

  private String inventoryStatusStr;

  private String warehouseName;

  public PageRequestDto() {
    this.page = 1;
    this.size = 10;
    this.type = "";
    this.keyword = "";
    this.quantityStatusStr = "";
    this.inventoryStatusStr = "";
    this.warehouseName = "";
  }

  // 스프랑 페이지 나누기 정보 저장 => Pageable
  // 1페이지는 0 부터 시작 그러므로
  // page 받은거에서 1을 뺀다

  public Pageable getPageable(Sort sort) {
    return PageRequest.of(page - 1, size, sort);
  }
}
