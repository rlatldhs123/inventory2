package com.example.inventory2.entity;

import com.example.inventory2.repository.InventoryLogRepository;
import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.LogType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
public class Inventory extends BaseEntity {

  @Id
  @SequenceGenerator(
    name = "inventory_seq_gen",
    sequenceName = "inventory_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "inventory_seq_gen"
  )
  private Long inventoryId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private InventoryStatus inventoryStatus;

  @Column(nullable = false)
  private int quantity;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "warehouse_id", nullable = false)
  private Warehouse warehouse;

  public void changeQuantity(int newQuantity) {
    this.quantity = newQuantity;
  }

  public void changeWarehouse(Warehouse newWarehouse) {
    this.warehouse = newWarehouse;
  }

  public void changeStatus(InventoryStatus newStatus) {
    this.inventoryStatus = newStatus;
  }
}
