package com.example.inventory2.entity;

import com.example.inventory2.status.WarehouseName;
import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Warehouse extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long warehouseId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private WarehouseName warehouseName;
}
