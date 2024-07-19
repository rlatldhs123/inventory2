package com.example.inventory2.service;

import com.example.inventory2.dto.PriceDTO;
import com.example.inventory2.entity.Price;
import java.util.List;

public interface PriceService {
  List<PriceDTO> findAllPrices();

  // 가격 정보 저장
  void save(PriceDTO priceDTO);

  public default PriceDTO entityToDto(Price price) {
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

  public default Price dtoToEntity(PriceDTO priceDTO) {
    if (priceDTO == null) {
      return null;
    }

    Price price = new Price();
    price.setPriceId(priceDTO.getPriceId());
    price.setSupplyPrice(priceDTO.getSupplyPrice());
    price.setDeliveryPrice(priceDTO.getDeliveryPrice());
    price.setNaverPrice(priceDTO.getNaverPrice());
    price.setDuty(priceDTO.getDuty());
    price.setShippingCost(priceDTO.getShippingCost());
    return price;
  }
}
