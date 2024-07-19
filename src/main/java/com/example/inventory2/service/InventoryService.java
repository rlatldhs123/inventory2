package com.example.inventory2.service;

import com.example.inventory2.dto.InventoryDTO;
import com.example.inventory2.dto.MoveDTO;
import com.example.inventory2.dto.PageRequestDto;
import com.example.inventory2.dto.PageResultDto;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.entity.Inventory;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.Warehouse;
import com.example.inventory2.status.WarehouseName;
import java.util.List;

public interface InventoryService {
  // 인벤토리 리스트 출력
  PageResultDto<InventoryDTO, Inventory> getInventoryList(
      PageRequestDto pageRequestDto);

  // 리팩토링한 인벤토리 리스트 가져오기 수정중
  // PageResultDto<ProductDTO, Object[]> getList(PageRequestDto requestDto);

  Long createInventory(InventoryDTO inventoryDTO);

  void moveInventory(MoveDTO moveDTO);

  // 리스트로 받은 ID 를 삭제
  void deleteInventories(List<Long> ids);

  // 인벤토리에서 이름과 코드
  List<InventoryDTO> searchInventories(String productCode, String productName);

  //
  List<InventoryDTO> getInventoriesByIds(List<Long> ids);

  default InventoryDTO entityToDto(Inventory inventory) {
    if (inventory == null) {
      return null;
    }
    return InventoryDTO
        .builder()
        .productId(inventory.getProduct().getProductId())
        .inventoryId(inventory.getInventoryId())
        // .inventoryStatus(inventory.getInventoryStatus())
        .inventoryStatus(inventory.getInventoryStatus())
        .quantity(inventory.getQuantity())
        .productCode(inventory.getProduct().getProductCode())
        .productName(inventory.getProduct().getProductName())
        .warehouseName(inventory.getWarehouse().getWarehouseName().name())
        .opt(inventory.getProduct().getOpt())

        .build();
  }

  default Inventory dtoToEntity(InventoryDTO inventoryDTO) {
    if (inventoryDTO == null) {
      return null;
    }
    Inventory inventory = new Inventory();
    inventory.setInventoryStatus(inventoryDTO.getInventoryStatus());
    inventory.setQuantity(inventoryDTO.getQuantity());

    Product product = new Product();
    product.setProductCode(inventoryDTO.getProductCode());
    product.setProductName(inventoryDTO.getProductName());
    inventory.setProduct(product);
    product.setOpt(inventoryDTO.getOpt());

    Warehouse warehouse = new Warehouse();
    warehouse.setWarehouseName(
        WarehouseName.valueOf(inventoryDTO.getWarehouseName()));
    inventory.setWarehouse(warehouse);

    return inventory;
  }
}
