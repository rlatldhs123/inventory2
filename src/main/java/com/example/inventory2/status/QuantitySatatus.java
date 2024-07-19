package com.example.inventory2.status;

public enum QuantitySatatus {
  // 데이터 다시 넣어야 해서 안전 == 원활
  원활, //  재고가 기준치보다 높을때
  품절, // 재고가 0 일시
  부족, // 재고가 기준
  경고,
  // 재고의 상태치를 정하는 기준 값은 하나 더 필요하고

  // 단종 같은 경우는 따로 뺴야 한다
}
