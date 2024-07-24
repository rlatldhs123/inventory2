package com.example.inventory2.service;

import com.example.inventory2.dto.InventoryDTO;
import com.example.inventory2.dto.MoveDTO;
import com.example.inventory2.dto.PageRequestDto;
import com.example.inventory2.dto.PageResultDto;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Inventory;
import com.example.inventory2.entity.Price;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.QInventory;
import com.example.inventory2.entity.QProduct;
import com.example.inventory2.entity.Warehouse;
import com.example.inventory2.repository.InventoryRepository;
import com.example.inventory2.repository.ProductRepository;
import com.example.inventory2.repository.WarehouseRepository;
import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.QuantitySatatus;
import com.example.inventory2.status.WarehouseName;
import com.querydsl.core.BooleanBuilder;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  private final WarehouseRepository warehouseRepository;

  private final ProductRepository productRepository;

  @Override
  public Long createInventory(InventoryDTO inventoryDTO) {
    // 상품 코드로 상품의 PK를 조회
    String code = inventoryDTO.getProductCode();

    Product product = productRepository.findProductIdByProductCode(code);

    // Product 엔티티 조회

    // 창고 이름으로 창고의 PK를 조회
    String warehouseNameStr = inventoryDTO.getWarehouseName();
    WarehouseName warehouseName = WarehouseName.valueOf(warehouseNameStr);
    Long warehouseId = warehouseRepository.findWarehouseIdByWarehouseName(
        warehouseName);

    // Warehouse 엔티티 조회
    Warehouse warehouse = warehouseRepository
        .findById(warehouseId)
        .orElseThrow(() -> new IllegalArgumentException("창고를 찾을 수 없습니다."));

    // Inventory 엔티티 생성
    Inventory inventory = Inventory
        .builder()
        .product(product) // 상품 설정
        .quantity(inventoryDTO.getQuantity())
        .inventoryStatus(inventoryDTO.getInventoryStatus())
        .warehouse(warehouse) // 창고 설정
        .build();

    product = check(product, inventory);

    // 인벤토리 저장
    Inventory savedInventory = inventoryRepository.save(inventory);
    // 수량 변화에 따른 상태 변화 저장
    product = productRepository.save(product);

    // 생성된 인벤토리의 ID 반환
    return savedInventory.getInventoryId();
  }

  // 수량에 따른 상태 체크 함수(재사용) 현재 크리에이트 만 적용 추후 업데이트 폐기에도 적용
  private Product check(Product product, Inventory inventory) {
    // 상태 변화
    if (inventory.getQuantity() >= product.getThreshold()) {
      product.setQuantityStatus(QuantitySatatus.원활);
    } else if (inventory.getQuantity() >= product.getThreshold2()) {
      product.setQuantityStatus(QuantitySatatus.부족);
    } else if (inventory.getQuantity() > 0) {
      product.setQuantityStatus(QuantitySatatus.경고);
    } else {
      product.setQuantityStatus(QuantitySatatus.품절);
    }

    return product;
  }

  @Override
  public List<InventoryDTO> searchInventories(
      String productCode,
      String productName) {
    List<Inventory> inventories = inventoryRepository.findByProductCodeAndProductName(
        productCode,
        productName);
    return inventories
        .stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  private InventoryDTO convertToDto(Inventory inventory) {
    return InventoryDTO
        .builder()
        // .opt(null)
        .inventoryId(inventory.getInventoryId())
        .inventoryStatus(inventory.getInventoryStatus())
        .quantity(inventory.getQuantity())
        .productCode(inventory.getProduct().getProductCode())
        .productName(inventory.getProduct().getProductName())
        .warehouseName(inventory.getWarehouse().getWarehouseName().name())
        .build();
  }

  @Override
  public void deleteInventories(List<Long> ids) {
    inventoryRepository.deleteAllById(ids);
  }

  @Override
  public List<InventoryDTO> getInventoriesByIds(List<Long> ids) {
    List<Inventory> inventories = inventoryRepository.findAllById(ids);
    return inventories
        .stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  public PageResultDto<InventoryDTO, Object[]> getInventoryList(
      PageRequestDto requestDto) {
    Page<Object[]> result = inventoryRepository.getInventoryList(requestDto.getType(), requestDto.getKeyword(),
        requestDto.getInventoryStatusStr(),
        requestDto.getWarehouseName(),
        requestDto.getPageable(Sort.by("inventoryId").descending()));

    Function<Object[], InventoryDTO> fn = (en -> entityToDto((Inventory) en[0],
        (Product) en[1], (Warehouse) en[2]));

    return new PageResultDto<>(result, fn);
  }

  @Override
  public void moveInventory(MoveDTO moveDTO) {
    moveDTO
        .getInvetoryList()
        .forEach(item -> {
          Inventory inventory = inventoryRepository
              .findById(item.getInventoryId())
              .get();
          inventory.setInventoryStatus(item.getInventoryStatus());
          // inventory.changeQuantity(item.getQuantity());
          // Product product=inventory.getProduct();

          // check(product, inventory);

          Warehouse warehouse = warehouseRepository.findByWarehouseName(
              WarehouseName.valueOf(item.getWarehouseName()));
          inventory.setWarehouse(warehouse);
          inventoryRepository.save(inventory);
        });
  }

  private BooleanBuilder getSearch(PageRequestDto requestDto) {
    BooleanBuilder builder = new BooleanBuilder();
    QInventory inventory = QInventory.inventory;

    // 검색 조건에서 필드를 가져옵니다.
    String type = requestDto.getType();
    String keyword = requestDto.getKeyword();

    // 기본 조건: inventoryId가 0보다 큰 항목들을 검색합니다.
    builder.and(inventory.inventoryId.gt(0L));

    if (type != null && type.trim().isEmpty()) {
      return builder;
    }
    BooleanBuilder conditionBuilder = new BooleanBuilder();

    if (type.contains("productCode")) {
      conditionBuilder.or(inventory.product.productCode.eq(keyword));
    }
    if (type.contains("productName")) {
      conditionBuilder.or(inventory.product.productName.contains(keyword));
    }

    InventoryStatus inventoryStatus = null;
    String inventoryStatusStr = requestDto.getInventoryStatusStr();

    if (inventoryStatusStr != null && inventoryStatusStr != "" &&
        !inventoryStatusStr.equals("전체")) {
      switch (inventoryStatusStr) {
        case "교환":
          inventoryStatus = InventoryStatus.교환;
          break;
        case "반품":
          inventoryStatus = InventoryStatus.반품;
          break;
        case "분실":
          inventoryStatus = InventoryStatus.분실;
          break;
        case "입고":
          inventoryStatus = InventoryStatus.입고;
          break;
        case "출고":
          inventoryStatus = InventoryStatus.출고;
          break;
        case "출고대기":
          inventoryStatus = InventoryStatus.출고대기;
          break;
        case "폐기":
          inventoryStatus = InventoryStatus.폐기;
          break;
        case "회송":
          inventoryStatus = InventoryStatus.회송;
          break;
        case "훼손":
          inventoryStatus = InventoryStatus.훼손;
          break;
        default:
          // 필요시 예외 처리나 기본값 설정
          throw new IllegalArgumentException(
              "Invalid inventory status: " + inventoryStatusStr);
      }
      if (inventoryStatus != null) {
        builder.and(inventory.inventoryStatus.eq(inventoryStatus));
      }
    }
    return builder;
  }

}
