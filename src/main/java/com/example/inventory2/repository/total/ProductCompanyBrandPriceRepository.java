package com.example.inventory2.repository.total;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCompanyBrandPriceRepository {
    // 전체 리스트
    Page<Object[]> getProductList(String type, String keyword, String quantityStatusStr, Pageable pageable);

    // 특정영화 조회
    List<Object[]> getProductRow(Long productId);
}
