package com.example.inventory2.dto.view;

public interface ProductListView {
  Priceview getPrice();
  ProductView getProduct();
  CompanyBrandView getCompanyBrand();
  //   형식 맞추기
  //   여러타입 한꺼번에 답기

  // 스프링 프로젝션

  // 링크 https://www.baeldung.com/spring-data-jpa-projections
}
