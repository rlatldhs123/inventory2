package com.example.inventory2.dto;

import com.example.inventory2.entity.Price;
import com.example.inventory2.status.Category;
import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.ProductOnlineCase;
import com.example.inventory2.status.QuantitySatatus;
import com.example.inventory2.status.Soldout;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

  private Long productId;
  private String productCode;
  private String productName;
  private String opt;
  private String productNum;
  private String components;
  private String extraPurchaseOption;
  private String remarks;
  private String modifyContent;
  private String mainImage;
  private ProductOnlineCase onlineCase;
  private Category category;
  private String manager;
  private int threshold;
  private QuantitySatatus quantityStatus;
  private int threshold2;
  private Soldout soldOut;

  //가격
  private Long purchaseCost;
  private Long supplyPrice;
  private Long deliveryPrice;
  private Long naverPrice;
  private Long duty;
  private Long shippingCost;

  //CompanyBrand
  private String companyName;
  private String brandName;
  private String brandCode;
  private String companyCode;
  private Long companyBrandId;

  //  인벤토리 DTO
  private InventoryStatus inventoryStatus;
  private Long inventoryId;
  private int quantity;
  private Long warehouseId;
}
