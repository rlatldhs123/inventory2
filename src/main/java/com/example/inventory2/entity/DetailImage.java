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
public class DetailImage extends BaseEntity {

  @Id
  @SequenceGenerator(
    name = "detail_image_seq_gen",
    sequenceName = "detail_image_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "detail_image_seq_gen"
  )
  private Long detailImageNum;

  private String detailImageUrl;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
}
