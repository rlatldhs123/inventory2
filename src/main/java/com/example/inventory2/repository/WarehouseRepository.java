package com.example.inventory2.repository;

import com.example.inventory2.entity.Warehouse;
import com.example.inventory2.status.WarehouseName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
  // 재고이동 필수 메소드
  Warehouse findByWarehouseName(WarehouseName warehouseName);

  // 인벤토리 추가에 필요한 메소드
  @Query(
    "SELECT w.warehouseId FROM Warehouse w WHERE w.warehouseName = :warehouseName"
  )
  Long findWarehouseIdByWarehouseName(
    @Param("warehouseName") WarehouseName warehouseName
  );
}
