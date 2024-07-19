package com.example.inventory2.entity;

import com.example.inventory2.status.Category;
import com.example.inventory2.status.ProductOnlineCase;
import com.example.inventory2.status.QuantitySatatus;
import com.example.inventory2.status.Soldout;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.apache.catalina.Manager;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.mapping.List;

@Data
@ToString(exclude = { "price", "companyBrand" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product extends BaseEntity {

  @Id
  @SequenceGenerator(
    name = "product_seq_gen",
    sequenceName = "product_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "product_seq_gen"
  )
  private Long productId;

  // 상품코드
  @Column(nullable = false, unique = true)
  private String productCode;

  // 상품 옵션
  @Column(nullable = false)
  private String opt;

  // 상품번호
  private String productNum;

  // 상품명
  private String productName;
  // 구성품
  private String components;

  // 추가구매 옵션
  private String extraPurchaseOption;
  //   비고
  private String remarks;

  @Column(nullable = false)
  private String modifyContent;

  private String mainImage;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductOnlineCase onlineCase;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category;

  private String manager;

  // 기준이 되는 재고수 원활 품절 단종

  //  threshold 보다높으면 원활
  // threshold 보다 낮으면 부족
  @ColumnDefault("100")
  private int threshold; // 안전기준

  //

  //  threshold2 보다높으면 부족
  // threshold2 보다 낮으면 경고
  // 총 재고량이 == 0 이면 품절
  @ColumnDefault("50")
  private int threshold2; // 위험 기준

  @ColumnDefault("'O'")
  @Enumerated(EnumType.STRING)
  private Soldout soldout;

  // 원할 단종 품절
  @Enumerated(EnumType.STRING)
  @ColumnDefault("'원활'")
  private QuantitySatatus quantityStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_brand_id", nullable = false)
  private CompanyBrand companyBrand;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "price_id", nullable = false, unique = true)
  private Price price;
}
