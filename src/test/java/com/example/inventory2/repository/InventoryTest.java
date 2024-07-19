package com.example.inventory2.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Answers.valueOf;

import com.example.inventory2.dto.CompanyBrandDTO;
import com.example.inventory2.dto.InventoryDTO;
import com.example.inventory2.dto.PageRequestDto;
import com.example.inventory2.dto.PageResultDto;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.dto.view.ProductListView;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Inventory;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.Warehouse;
import com.example.inventory2.service.CompanyBrandService;
import com.example.inventory2.service.InventoryService;
import com.example.inventory2.service.ProductService;
import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.QuantitySatatus;
import com.example.inventory2.status.WarehouseName;
import groovy.transform.ToString;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class InventoryTest {

  @Autowired
  private InventoryRepository inventoryRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private WarehouseRepository warehouseRepository;

  @Autowired
  private CompanyBrandRepository companyBrandRepository;

  @Autowired
  private CompanyBrandService companyBrandService;

  @Autowired
  private ProductService productService;

  @Autowired
  private InventoryService inventoryService;

  // @Test
  // public void testList() {
  // List<Object[]> list = inventoryRepository.findInventoryList();

  // for (Object[] objects : list) {
  // System.out.println(Arrays.toString(objects));
  // }
  // }

  // Transactional 을 쓰는이유는(fetch = FetchType.LAZY) 을 쓰고 있기 때문이다

  @Transactional
  @Test
  public void testInventoryList() {

  }

  @Transactional
  @Test
  public void testProductList() {
    // List<ProductListView> list = productRepository.findAllProduct();

    // for (ProductListView objects : list) {
    // System.out.println(objects.getPrice().getPriceId());
    // }
    PageRequestDto dto = PageRequestDto
        .builder()
        .type("productCode")
        .keyword("JSLIBL02")
        .quantityStatusStr("")
        .page(1)
        .size(3)
        .build();

    PageResultDto<ProductDTO, Object[]> res = productService.getList(dto);

    res.getDtoList().forEach(dto1 -> System.out.println(dto1));
    // List<Product> list = productRepository.findAll();
    // for (Product product : list) {
    // System.out.println(product);
    // System.out.println(
    // product.getCompanyBrand().getCompany().getCompanyName()
    // );
    // }
  }

  @Test
  public void findByProductCode_Test() {
    String code = "QW";
    List<Product> list = productRepository.findByProductCodeContaining(code);

    for (Product product : list) {
      System.out.println(product);
    }
  }

  @Test
  public void test() {
    List<CompanyBrandDTO> list = companyBrandService.getCompanyBrandList();

    for (CompanyBrandDTO companyBrandDTO : list) {
      System.out.println(companyBrandDTO);
    }
  }

  @Test
  public void findByCompanyAndBrandTest() {
    // <<<<<<<<<< 테스트를 위한 가상의 데이터 설정
    String companyName = "지성아이엔씨";
    String companyCode = "JS";
    String brandName = "플라이토";
    String brandCode = "F";

    // <<<<<<<<<< 메서드 호출 및 결과 출력
    CompanyBrand companyBrand = companyBrandRepository.findByCompanyAndBrand(
        companyName,
        companyCode,
        brandName,
        brandCode);

    System.out.println(companyBrand);
  }

  // @Test
  // public void findInventoryListTest() {
  // List<Object[]> list = inventoryRepository.findInventoryList();

  // for (Object[] objects : list) {
  // System.out.println(Arrays.toString(objects));
  // }
  // }

  // @Test
  // public void searchProducts() {
  // String code = "JSLIWH01";
  // String name = "국산 루나리스 3D LED 인테리어 벽시계 삼성전구 38cm";

  // List<Object[]> list = productRepository.searchProducts(code, name);

  // for (Object[] objects : list) {
  // System.out.println(Arrays.toString(objects));
  // }
  // }

  @Test
  public void findAllTest() {
    List<Inventory> list = inventoryRepository.findAll();

    System.out.println(list.get(0));
  }

  @Test
  public void findByQuantityStatus() {
    // Pageable 객체 초기화
    Pageable pageable = PageRequest.of(0, 10); // 첫 번째 페이지, 페이지당 10개 항목

    // 경고 상태의 제품 목록 조회
    Page<Product> list = productRepository.findByQuantityStatus(
        QuantitySatatus.품절,
        pageable);

    // 결과 출력
    for (Product product : list) {
      System.out.println(product);
    }
  }

  @Test
  @Transactional
  public void productReadTeset() {
    String productCode = "QWFIMP03";

    ProductDTO productDto = productService.getProduct(productCode);

    System.out.println(productDto);
  }

  // 테스트 완료
  @Test
  public void testFindByProductCodeAndProductName() {
    // 테스트할 메소드 호출
    List<Inventory> inventories = inventoryRepository.findByProductCodeAndProductName(
        "",
        "국산");

    // 결과 검증

    for (Inventory inventory : inventories) {
      System.out.println(inventory);
    }
  }

  @Test
  public void findProductIdByProductCodeTESt() {
    String code = "JSFIBL03";
    String warehouseName = "WAREHOUSE_102";

    InventoryDTO inventoryDTO = InventoryDTO
        .builder()
        .quantity(30)
        .inventoryStatus(InventoryStatus.반품)
        .productCode(code)
        .warehouseName(warehouseName)
        .build();

    Long invenId = inventoryService.createInventory(inventoryDTO);

    System.out.println(invenId);
  }

  @Test
  public void findWarehouseIdByWarehouseNameTEST() {
    Warehouse warehouse = warehouseRepository.findByWarehouseName(
        WarehouseName.WAREHOUSE_102);

    // 출력 (선택 사항)
    System.out.println("Warehouse ID: " + warehouse.getWarehouseId());
  }

  // @Test
  // public void findByInventoryStatusTEST() {
  // Inventory status = inventoryRepository.findByInventoryStatus();

  // System.out.println(status);
  // }

  @Test
  public void teset() {
    PageRequestDto page = PageRequestDto
        .builder()
        .keyword("")
        .size(1)
        .page(1)
        .type("opt")
        .build();
    PageResultDto<InventoryDTO, Inventory> moves = inventoryService.getInventoryList(
        page);
  }
}
