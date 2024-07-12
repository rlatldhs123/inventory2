package com.example.inventory2.service;

import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.entity.Product;
import java.util.List;

public interface ProductService {
  List<Object[]> getAllProductDetails();

  public default ProductDTO entityToDto(Product product) {
    if (product == null) {
      return null;
    }

    return ProductDTO
      .builder()
      .productId(product.getProductId())
      .productCode(product.getProductCode())
      .productName(product.getProductName())
      .productOption(product.getOpt())
      .productNum(product.getProductNum())
      .components(product.getComponents())
      .extraPurchaseOption(product.getExtraPurchaseOption())
      .remarks(product.getRemarks())
      .modifyContent(product.getModifyContent())
      .mainImage(product.getMainImage())
      .onlineCase(product.getOnlineCase().name())
      .category(product.getCategory().name())
      .manager(product.getManager())
      .threshold(product.getThreshold())
      .quantityStatus(product.getQuantityStatus().name())
      .build();
  }
}
