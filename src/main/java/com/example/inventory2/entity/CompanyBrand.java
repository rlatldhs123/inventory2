package com.example.inventory2.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
public class CompanyBrand extends BaseEntity {

  @Id
  @SequenceGenerator(
    name = "company_brand_seq_gen",
    sequenceName = "company_brand_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "company_brand_seq_gen"
  )
  private Long companyBrandId;

  @ManyToOne
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @ManyToOne
  @JoinColumn(name = "brand_id", nullable = true)
  private Brand brand;
}
