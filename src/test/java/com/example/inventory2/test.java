package com.example.inventory2;

import com.example.inventory2.entity.Brand;
import com.example.inventory2.entity.Company;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Inventory;
import com.example.inventory2.entity.Price;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.Warehouse;
import com.example.inventory2.repository.BrandRepository;
import com.example.inventory2.repository.CompanyBrandRepository;
import com.example.inventory2.repository.CompanyRepository;
import com.example.inventory2.repository.DetailImageRepository;
import com.example.inventory2.repository.InventoryLogRepository;
import com.example.inventory2.repository.InventoryRepository;
import com.example.inventory2.repository.PriceRepository;
import com.example.inventory2.repository.ProductRepository;
import com.example.inventory2.repository.WarehouseRepository;
import com.example.inventory2.status.Category;
import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.ProductOnlineCase;
import com.example.inventory2.status.WarehouseName;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;
import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {

  @Autowired
  private WarehouseRepository warehouseRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private BrandRepository brandRepository;

  @Autowired
  private CompanyBrandRepository companyBrandRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private PriceRepository priceRepository;

  @Autowired
  private InventoryRepository inventoryRepository;

  @Autowired
  private DetailImageRepository detailImageRepository;

  @Autowired
  private InventoryLogRepository inventoryLogRepository;

  // 브랜드 추가
  @Test
  public void insertBrand() {
    LongStream
      .rangeClosed(1, 20)
      .forEach(i -> {
        Brand brand = Brand
          .builder()
          .brandId(i)
          .brandCode("F")
          .brandName("플라이토")
          .build();

        brandRepository.save(brand);
      });
  }

  // 업체 추가
  @Test
  public void insertCompany() {
    LongStream
      .rangeClosed(1, 2)
      .forEach(i -> {
        Company company = Company
          .builder()
          .companyId(i)
          .companyCode("JS")
          .companyName("지성아이앤씨")
          .build();

        companyRepository.save(company);
      });
  }

  // 창고 추가
  @Test
  public void insertWarehouse() {
    LongStream
      .rangeClosed(1, 9)
      .forEach(i -> {
        Warehouse warehouse = Warehouse
          .builder()
          .warehouseId(i)
          .warehouseName(WarehouseName.WAREHOUSE_101)
          .build();

        warehouseRepository.save(warehouse);
      });
  }

  @Test
  public void insertCompanyBrand() {
    LongStream
      .rangeClosed(1, 9)
      .forEach(i -> {
        CompanyBrand companyBrand = CompanyBrand.builder().build();

        companyBrandRepository.save(companyBrand);
      });
  }

  @Test
  public void insertProduct() {
    LongStream
      .rangeClosed(1, 4)
      .forEach(i -> {
        CompanyBrand companyBrand = CompanyBrand
          .builder()
          .companyBrandId(i)
          .build();
        Product product = Product
          .builder()
          .productId(i)
          .productCode("JSLIWH01")
          .opt("화이트_쿨화이트 LED")
          .productNum("01")
          .productName("국산 루나리스 3D LED 인테리어 벽시계 삼성전구 38cm")
          .components("아답터포함")
          .extraPurchaseOption(
            "거치대-1000원 / 연장케이블80cm-1500원  / 벽지핀2P-2000원 / 브라켓 3000원 / 대리석핀 2P 500원"
          )
          .remarks("비고1")
          .modifyContent("실제 지도가 24,900")
          .mainImage(
            "https://jishop.co.kr/web/product/small/202302/067924254f279d82235e51bf44b2b156.jpg"
          )
          .onlineCase(ProductOnlineCase.가능)
          .category(Category.가구_인테리어)
          .manager("김시온")
          .companyBrand(companyBrand)
          .build();

        productRepository.save(product);
      });
  }

  //  price 삽입

  @Test
  public void insertPrice() {
    LongStream
      .rangeClosed(59, 76)
      .filter(i -> i != 59) // 59번을 제외
      .forEach(i -> {
        Product product = Product.builder().productId(i).build();

        // Product 엔티티를 먼저 저장
        productRepository.save(product);

        long priceId = i - 50;

        Price price = Price
          .builder()
          .priceId(priceId + 8) // 이미 8번까지 돌았기 때문에 ID를 이어서 사용
          .purchaseCost(50000L + (priceId * 1000))
          .supplyPrice(50000L + (priceId * 3000))
          .deliveryPrice(3000L)
          .naverPrice(50000L + (priceId * 2400))
          .duty(5000 + (priceId * 100))
          .shippingCost(100000 + (priceId * 1000))
          .build();

        priceRepository.save(price);
      });
  }

  // 창고

  // 재고화 더미 데이터 삽입

  @Test
  @Transactional
  public void insertInventory() {
    Set<Long> usedProductIds = new HashSet<>();
    Set<Long> usedWarehouseIds = new HashSet<>();
    InventoryStatus[] statuses = InventoryStatus.values();

    LongStream
      .rangeClosed(3010, 4010)
      .forEach(i -> {
        // 1에서 100 사이의 랜덤 정수를 생성합니다.
        int 수량랜덤 = ThreadLocalRandom.current().nextInt(1, 101);

        // 상품 ID 랜덤 값을 생성하고 59를 제외합니다.
        Long 상품id랜덤;
        do {
          상품id랜덤 = ThreadLocalRandom.current().nextLong(51, 77);
        } while (상품id랜덤 == 59 || usedProductIds.contains(상품id랜덤));
        usedProductIds.add(상품id랜덤);

        // 창고 ID 랜덤 값을 생성하고 중복을 제외합니다.
        Long 창고id랜덤;
        do {
          창고id랜덤 = ThreadLocalRandom.current().nextLong(19, 28);
        } while (usedWarehouseIds.contains(창고id랜덤));
        usedWarehouseIds.add(창고id랜덤);

        Product product = Product.builder().productId(상품id랜덤).build();
        Warehouse warehouse = Warehouse
          .builder()
          .warehouseId(창고id랜덤)
          .build();

        // InventoryStatus 값을 차례대로 할당
        InventoryStatus status = statuses[(int) ((i - 1) % statuses.length)];

        Inventory inventory = Inventory
          .builder()
          .quantity(수량랜덤)
          .product(product)
          .inventoryId(i)
          .warehouse(warehouse)
          .inventoryStatus(status) // inventoryStatus 설정
          .build();

        inventoryRepository.save(inventory);
      });
  }
}
