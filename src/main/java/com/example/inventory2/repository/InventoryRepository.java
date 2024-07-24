package com.example.inventory2.repository;

import com.example.inventory2.entity.Inventory;
import com.example.inventory2.entity.Warehouse;
import com.example.inventory2.repository.total.InventoryProductWarehouseRepository;
import com.example.inventory2.status.InventoryStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface InventoryRepository
        extends JpaRepository<Inventory, Long>, InventoryProductWarehouseRepository {
    // List<InventoryStatus> findByInventoryStatus();
    // @Query(
    // value = "SELECT " +
    // "i.QUANTITY AS quantity, " +
    // "i.INVENTORY_STATUS AS inventoryStatus, " +
    // "w.WAREHOUSE_NAME AS warehouseName, " +
    // "p.PRODUCT_CODE AS productCode, " +
    // "p.PRODUCT_NAME AS productName " +
    // "FROM inventory_user.inventory i " +
    // "LEFT OUTER JOIN inventory_user.product p " +
    // "ON i.product_id = p.product_id " +
    // "LEFT OUTER JOIN inventory_user.warehouse w " +
    // "ON i.warehouse_id = w.warehouse_id",
    // nativeQuery = true
    // )
    // List<Object[]> findList();

    // 이동 시킬때 필요한 메소드이다 JPA 자동 기능으로 만들었다
    Inventory findByProduct_ProductCodeAndWarehouse(
            String productCode,
            Warehouse warehouse);

    // SELECT
    // *
    // FROM
    // inventory i
    // JOIN product p ON
    // i.product_id = p.product_id
    // WHERE
    // LOWER(p.product_code) LIKE '%' || (LOWER('JS')) || '%'
    // AND (p.product_name LIKE '%시계%');

    // 인벤토리를 제품 코드와 이름으로 검색하는 네이티브 쿼리
    @Query(value = "SELECT i.*, p.product_code AS product_code, p.product_name AS product_name " +
            "FROM inventory i " +
            "JOIN product p ON i.product_id = p.product_id " +
            "WHERE LOWER(p.product_code) LIKE '%' || LOWER(:productCode) || '%' " +
            "AND LOWER(p.product_name) LIKE '%' || LOWER(:productName) || '%'", nativeQuery = true)
    List<Inventory> findByProductCodeAndProductName(
            @Param("productCode") String productCode,
            @Param("productName") String productName);
}
