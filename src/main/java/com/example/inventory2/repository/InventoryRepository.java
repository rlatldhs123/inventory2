package com.example.inventory2.repository;

import com.example.inventory2.entity.Inventory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  //   @Query(
  //     value = "SELECT p.productName, p.manager, i.inventoryStatus, i.lastModifiedDate, w.warehouseName " +
  //     "FROM Inventory i " +
  //     "JOIN Product p ON i.productId = p.productd " +
  //     "JOIN Warehouse w ON i.warehouseId = w.warehouseId",
  //     nativeQuery = true
  //   )
  //   List<Object[]> findInventoryList();

  //   JOIN Warehouse w ON i.warehouseId = w.warehouseId

  @Query("SELECT i,p FROM Inventory i JOIN i.product p ")
  List<Object[]> findInventoryList();
}
