package com.example.inventory2.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Price extends BaseEntity {

  @Id
  @SequenceGenerator(
    name = "price_seq_gen",
    sequenceName = "price_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "price_seq_gen"
  )
  private Long priceId;

  // 매입가
  @Column(nullable = false)
  private Long purchaseCost;

  private Long supplyPrice;
  private Long deliveryPrice;
  private Long naverPrice;
  // 관세
  private Long duty;
  // 운임
  private Long shippingCost;
}
