package com.example.inventory2.dto.view;

public interface ProductView {
  Long getProductId();
  String getProductCode();
  String getProductName();
  String getProductOption();
  String getProductOnlineCase();
  String getOnlineCase();
  String getQuantityStatus();
  String getProductNum();
  String getComponents();
  String getExtraPurchaseOption();
  String getRemarks();
  String getModifyContent();
  String getMainImage();
  String getCategory();
  String getThreshold();
  String getBrandName();
  String getBrandCode();
  String getCompanyName();
  String getCompanyCode();
  Long getPurchaseCost();
  Long getSupplyPrice();
  Long getDeliveryPrice();
  Long getNaverPrice();
  Long getDuty();
  Long getShippingCost();
}
