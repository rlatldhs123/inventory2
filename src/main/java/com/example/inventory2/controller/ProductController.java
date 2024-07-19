package com.example.inventory2.controller;

import com.example.inventory2.dto.BrandDTO;
import com.example.inventory2.dto.CompanyBrandDTO;
import com.example.inventory2.dto.CompanyDTO;
import com.example.inventory2.dto.InventoryDTO;
import com.example.inventory2.dto.MoveDTO;
import com.example.inventory2.dto.PageRequestDto;
import com.example.inventory2.dto.PageResultDto;
import com.example.inventory2.dto.PriceDTO;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.dto.ProductInventoryDTO;
import com.example.inventory2.dto.WarehouseDTO;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Inventory;
import com.example.inventory2.entity.Product;
import com.example.inventory2.repository.WarehouseRepository;
import com.example.inventory2.service.BrandService;
import com.example.inventory2.service.CompanyBrandService;
import com.example.inventory2.service.CompanyService;
import com.example.inventory2.service.InventoryService;
import com.example.inventory2.service.PriceService;
import com.example.inventory2.service.ProductService;
import com.example.inventory2.service.WarehouseService;
import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.QuantitySatatus;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class ProductController {

  private final ProductService productService;
  private final CompanyBrandService companyBrandService;
  private final PriceService priceService;
  private final InventoryService inventoryService;

  private final WarehouseService warehouseService;

  private final BrandService brandService;

  private final CompanyService companyService;

  private final WarehouseRepository warehouseRepository;

  // @GetMapping("/list")
  // public void getList(Model model, PageRequestDto requestDto) {
  // log.info("list 요청");

  // PageResultDto<GuestBookDto, GuestBook> result = service.getList(requestDto);

  // model.addAttribute("result", result);

  // }

  @GetMapping("/product_list")
  public void list(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      Model model) {

    log.info("전체 + 검색 {}", pageRequestDto);

    PageResultDto<ProductDTO, Object[]> result = productService.getList(pageRequestDto);

    model.addAttribute("products", result);
    // model.addAttribute("selectedQuantityStatus", status);

  }

  // 재고 이동 리스트

  @GetMapping("/move_list")
  public void move_list(
      Model model,
      InventoryDTO inventoryDTO,
      WarehouseDTO warehouseDTO,
      PageRequestDto pageRequestDto) {
    PageResultDto<InventoryDTO, Inventory> moves = inventoryService.getInventoryList(
        pageRequestDto);
    List<WarehouseDTO> warehouses = warehouseService.getAllWarehouses();

    model.addAttribute("moveList", moves);
    model.addAttribute("warehouses", warehouses);

    log.info("재고 이동 리스트 {} {} {]", moves, warehouses);
  }

  // @PostMapping("/move")
  // public String moveInventory(
  // @RequestParam("productIds") List<String> productIds, // HTML 폼에서 제출된
  // productIds 파라미터를 리스트로 받습니다. 이는 사용자가 선택한 제품의 코드 목록입니다.
  // @RequestParam Map<String, String> allParams, // 모든 요청 파라미터를 맵으로 받습니다. 각 제품
  // 코드에 대한 다른 파라미터 값을 이 맵에서 조회할 수 있습니다.
  // Model model
  // ) { // 뷰에 데이터를 전달하기 위한 모델 객체입니다. 현재 메서드에서는 사용되지 않지만, 필요에 따라 추가 데이터를 뷰에 전달할 수
  // 있습니다.
  // // 사용자가 선택한 각 제품 코드에 대해 루프를 돌면서 각각의 재고 이동 작업을 처리합니다.
  // for (String productCode : productIds) {
  // // 각 제품에 대해 fromWarehouse 파라미터를 조회합니다.
  // String fromWarehouse = allParams.get(
  // "fromWarehouse[" + productCode + "]"
  // );

  // // 각 제품에 대해 status 파라미터를 조회합니다.
  // String status = allParams.get("status[" + productCode + "]");

  // // 각 제품에 대해 toWarehouse 파라미터를 조회합니다.
  // String toWarehouse = allParams.get("toWarehouse[" + productCode + "]");

  // // 조회한 파라미터 값을 이용하여 moveInventory 서비스 메서드를 호출합니다. 이 메서드는 실제 재고 이동 작업을 처리합니다.
  // inventoryService.moveInventory(
  // productCode,
  // fromWarehouse,
  // status,
  // toWarehouse
  // );
  // }

  // // 재고 이동 작업이 완료되면 move_list 페이지로 리다이렉트합니다. 이는 재고 이동 작업 후 사용자가 재고 목록 페이지로
  // 이동하도록 합니다.
  // return "redirect:/inventory/move_list";
  // }

  @PostMapping("/move")
  public String moveInventory(MoveDTO moveDTO) {
    log.info("도착 {}", moveDTO);

    inventoryService.moveInventory(moveDTO);

    // 재고 이동 작업이 완료되면 move_list 페이지로 리다이렉트합니다. 이는 재고 이동 작업 후 사용자가 재고 목록 페이지로 이동하도록
    // 합니다.
    return "redirect:/inventory/move_list";
  }

  // 인벤토리 리스트
  @GetMapping("/inventory_list")
  public void getInventoryList(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      Model model,
      InventoryDTO inventoryDto) {
    PageResultDto<InventoryDTO, Inventory> inventoryList = inventoryService.getInventoryList(
        pageRequestDto);

    model.addAttribute("inventoryList", inventoryList);
    log.info("인벤토리 리스트 요청 {}", inventoryDto);
  }

  // 문제 1
  // 상품 생성 겟 요청
  @GetMapping("/create_product")
  public void getCreate_product(Model model) {
    List<BrandDTO> brandList = brandService.getBrandList();
    List<CompanyDTO> companyList = companyService.getCompanyList();

    model.addAttribute("brandList", brandList);
    model.addAttribute("companyList", companyList);

    log.info("상품 추가  요청 {}");
  }

  // 상품 생성 포스트 요청
  @PostMapping("/create_post")
  public String createProduct(ProductDTO productDTO) {
    log.info("product {}", productDTO);

    Long pid = productService.productCreate(productDTO);

    log.info("new product", pid);

    // productDTO.setCompanyBrandId(companyBrand.getCompanyBrandId());

    // Long productId = productService.productCreate(productDTO);

    return "redirect:/inventory/product_list";
  }

  // 상품 1개 상세 리드
  @GetMapping("/product_read")
  public String productRead(Model model, String productCode) {
    ProductDTO product = productService.getProduct(productCode);
    model.addAttribute("product", product);
    return "inventory/product_read"; // product_read.html 템플릿으로 반환
  }

  @GetMapping("/create_inventory")
  public void CreateInventory(Model model) {
    List<WarehouseDTO> warehouseList = warehouseService.getAllWarehouses();

    // 창고 리스트 선택 셀렉 옵션 부르기
    model.addAttribute("warehouseList", warehouseList);

    log.info("재고 생성 요청");
  }

  @PostMapping("/create_inventory")
  public String createInventory(
      Model model,
      InventoryDTO inventoryDTO,
      RedirectAttributes rttr) {
    log.info("재고생성 포스트 요청 {}", inventoryDTO);
    try {
      // 인벤토리 생성 서비스 호출
      Long inventoryId = inventoryService.createInventory(inventoryDTO);

      // 성공 메시지를 모델에 추가
      rttr.addFlashAttribute(
          "message",
          "인벤토리가 성공적으로 생성되었습니다. ID: " + inventoryId);
      return "redirect:/inventory/inventory_list";
    } catch (Exception e) {
      // 오류 메시지를 모델에 추가
      model.addAttribute(
          "error",
          "인벤토리 생성 중 오류가 발생했습니다: " + e.getMessage());
      return "inventory/create_inventory";
    }
  }

  // 인벤토리 총합 구하기 리스트 get 요청
  @GetMapping("/inventory_total_list")
  public void searchProducts(
      @RequestParam(required = false) String productCode,
      @RequestParam(required = false) String productName,
      Model model) {
    List<ProductInventoryDTO> products = productService.searchProducts(
        productCode != null ? productCode : "",
        productName != null ? productName : "");

    long totalPurchaseCost = 0;
    long totalSupplyPrice = 0;
    long totalNaverPrice = 0;

    for (ProductInventoryDTO product : products) {
      totalPurchaseCost += product.getPurchaseCost() * product.getTotalQuantity();
      totalSupplyPrice += product.getSupplyPrice() * product.getTotalQuantity();
      totalNaverPrice += product.getNaverPrice() * product.getTotalQuantity();
    }

    model.addAttribute("inventoryList", products);
    model.addAttribute("totalPurchaseCost", totalPurchaseCost);
    model.addAttribute("totalSupplyPrice", totalSupplyPrice);
    model.addAttribute("totalNaverPrice", totalNaverPrice);
  }

  @GetMapping("/delete_inventory")
  public void delete_inventory(
      @RequestParam(defaultValue = "") String productCode,
      @RequestParam(defaultValue = "") String productName,
      Model model) {
    log.info("재고 폐기 처분 {}");

    List<InventoryDTO> inventories = inventoryService.searchInventories(
        productCode,
        productName);
    model.addAttribute("inventoryList", inventories);
  }

  @PostMapping("/delete")
  public String deleteInventories(@RequestBody List<Long> ids) {
    inventoryService.deleteInventories(ids);
    return "redirect:/inventory/inventory_list";
  }
}
