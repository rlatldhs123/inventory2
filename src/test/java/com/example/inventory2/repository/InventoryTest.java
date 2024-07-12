package com.example.inventory2.repository;

import com.example.inventory2.dto.view.ProductListView;
import com.example.inventory2.entity.Product;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class InventoryTest {

  @Autowired
  private InventoryRepository inventoryRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private WarehouseRepository warehouseRepository;

  @Test
  public void testList() {
    List<Object[]> list = inventoryRepository.findInventoryList();

    for (Object[] objects : list) {
      System.out.println(Arrays.toString(objects));
    }
  }

  // Transactional 을 쓰는이유는(fetch = FetchType.LAZY) 을 쓰고 있기 때문이다
  @Transactional
  @Test
  public void testProductList() {
    // List<ProductListView> list = productRepository.findAllProduct();

    // for (ProductListView objects : list) {
    //   System.out.println(objects.getPrice().getPriceId());
    // }

    List<Product> list = productRepository.findAll();
    for (Product product : list) {
      System.out.println(product);
      System.out.println(
        product.getCompanyBrand().getCompany().getCompanyName()
      );
    }
  }
}
