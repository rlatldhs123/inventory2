package com.example.inventory2.repository;

import com.example.inventory2.dto.view.ProductListView;
import com.example.inventory2.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query(
    "SELECT p, pr, cb FROM Product p " +
    "JOIN p.companyBrand cb " +
    "JOIN p.price pr"
  )
  List<ProductListView> findAllProduct();
  // @Query("SELECT p FROM Product p ")
  // List<ProductList> findAllProduct();
}
