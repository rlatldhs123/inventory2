package com.example.inventory2.entity;

import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.LogType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InventoryLog extends BaseEntity {

  @Id
  @SequenceGenerator(
    name = "inventory_log_seq_gen",
    sequenceName = "inventory_log_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "inventory_log_seq_gen"
  )
  private Long inventoryLogId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private InventoryStatus inventoryStatus;

  // 로그가 남을시 어떤 사유에 의한 로그인지 확인
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LogType logType;

  @Column
  private int quantityAdd; // 추가되는 재고

  @Column
  private int quantitySub; // 빠지는 재고

  @ManyToOne
  @JoinColumn(name = "inventory_id", nullable = false)
  private Inventory inventory;
}
