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
public class Company extends BaseEntity {

  @Id
  @SequenceGenerator(
    name = "company_seq_gen",
    sequenceName = "company_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "company_seq_gen"
  )
  private Long companyId;

  @Column(nullable = false)
  private String companyName;

  @Column(nullable = false)
  private String companyCode;
}
