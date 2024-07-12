package com.example.inventory2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
public class Brand extends BaseEntity {

  @Id
  @SequenceGenerator(
    name = "brand_seq_gen",
    sequenceName = "brand_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "brand_seq_gen"
  )
  private Long brandId;

  @Column(nullable = false)
  private String brandName;

  @Column(nullable = false)
  private String brandCode;
}
