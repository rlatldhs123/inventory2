package com.example.inventory2.service;

import com.example.inventory2.dto.CompanyBrandDTO;
import com.example.inventory2.dto.CompanyDTO;
import com.example.inventory2.dto.PriceDTO;
import com.example.inventory2.dto.ProductDTO;
import com.example.inventory2.dto.WarehouseDTO;
import com.example.inventory2.entity.Company;
import com.example.inventory2.entity.CompanyBrand;
import com.example.inventory2.entity.Price;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.Warehouse;
import java.util.List;

public interface PriceService {
  default PriceDTO entityToDto(Price price) {
    if (price == null) {
      return null;
    }

    return PriceDTO
      .builder()
      .priceId(price.getPriceId())
      .supplyPrice(price.getSupplyPrice())
      .deliveryPrice(price.getDeliveryPrice())
      .naverPrice(price.getNaverPrice())
      .duty(price.getDuty())
      .shippingCost(price.getShippingCost())
      .build();
  }
}
