// package com.example.inventory2.repository.total;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

// import com.example.inventory2.entity.Inventory;
// import com.example.inventory2.entity.Product;
// import com.example.inventory2.entity.QInventory;
// import com.example.inventory2.entity.QProduct;
// import com.example.inventory2.entity.QWarehouse;
// import com.example.inventory2.status.InventoryStatus;
// import com.example.inventory2.status.QuantitySatatus;
// import com.example.inventory2.status.WarehouseName;
// import com.querydsl.core.BooleanBuilder;
// import com.querydsl.core.Tuple;
// import com.querydsl.core.types.Order;
// import com.querydsl.core.types.OrderSpecifier;
// import com.querydsl.core.types.dsl.PathBuilder;
// import com.querydsl.jpa.JPQLQuery;

// import lombok.extern.log4j.Log4j2;

// @Log4j2

// public class InventoryProductWarehouseRepositoryImpl extends QuerydslRepositorySupport

//         implements InventoryProductWarehouseRepository {

//     public InventoryProductWarehouseRepositoryImpl() {
//         super(Inventory.class);

//     }

//     @Override
//     public Page<Object[]> getInventoryList(String type, String keyword, String inventorystatusStr,
//             String warehouseName,
//             Pageable pageable) {

//         log.info("=================================");
//         log.info("재고 전체 리스트 {},{},{} {}", type, keyword, inventorystatusStr,
//                 warehouseName);

//         // Q클래스나열
//         QInventory inventory = QInventory.inventory;
//         QProduct product = QProduct.product;
//         QWarehouse warehouse = QWarehouse.warehouse;

//         JPQLQuery<Inventory> query = from(inventory);
//         query.leftJoin(product).on(inventory.product.eq(product));
//         query.leftJoin(warehouse).on(inventory.warehouse.eq(warehouse));

//         // 해당 순서대로 서비스에서 인덱싱 [] 해서 가져옴
//         JPQLQuery<Tuple> tuple = query.select(inventory, product, warehouse);

//         // 검색 조건을 추가하기 위한 BooleanBuilder 객체를 생성

//         BooleanBuilder builder = new BooleanBuilder();
//         // 기본 조건을 추가: 모든 inventoryId 0보다 큰 조건
//         builder.and(inventory.inventoryId.gt(0L));

//         // type과 키워드를 이용한 추가조건

//         BooleanBuilder conditionBuilder = new BooleanBuilder();
//         if (type.contains("productCode")) {
//             conditionBuilder.or(product.productCode.containsIgnoreCase(keyword));
//         }
//         if (type.contains("productName")) {
//             conditionBuilder.or(product.productName.contains(keyword));
//         }

//         // quantityStatusStr이 빈 문자열인 경우 현재 조건들만 반환
//         // if (quantityStatusStr.length() == 0) return builder;

//         // QuantitySatatus 변수를 초기화
//         InventoryStatus inventoryStatus = null;

//         // quantityStatusStr이 "전체"가 아닌 경우에만 상태 조건을 추가
//         // quantityStatus 값이 null이 아닌 경우에만 조건을 추가

//         if (inventorystatusStr != null && inventorystatusStr.isEmpty() &&
//                 !inventorystatusStr.equals("전체")) {
//             // quantityStatusStr 값에 따라 적절한 QuantitySatatus 값을 설정
//             switch (inventorystatusStr) {
//                 case "교환":
//                     inventoryStatus = InventoryStatus.교환;
//                     break;
//                 case "반품":
//                     inventoryStatus = InventoryStatus.반품;
//                     break;
//                 case "분실":
//                     inventoryStatus = InventoryStatus.분실;
//                     break;
//                 case "입고":
//                     inventoryStatus = InventoryStatus.입고;
//                     break;
//                 case "출고":
//                     inventoryStatus = InventoryStatus.출고;
//                     break;
//                 case "출고대기":
//                     inventoryStatus = InventoryStatus.출고대기;
//                     break;
//                 case "폐기":
//                     inventoryStatus = InventoryStatus.폐기;
//                     break;
//                 case "훼손":
//                     inventoryStatus = InventoryStatus.훼손;
//                     break;
//             }

//             // inventoryStatus 값이 null이 아닌 경우에만 조건을 추가

//             conditionBuilder.or(inventory.inventoryStatus.eq(inventoryStatus));
//         }

//         WarehouseName warehouseNameEnum = null;

//         if (warehouseName != null && warehouseName.isEmpty() &&
//                 !warehouseName.equals("전체")) {
//             // quantityStatusStr 값에 따라 적절한 QuantitySatatus 값을 설정
//             switch (warehouseName) {
//                 case "WAREHOUSE_101":
//                     warehouseNameEnum = WarehouseName.WAREHOUSE_101;
//                     break;
//                 case "WAREHOUSE_102":
//                     warehouseNameEnum = WarehouseName.WAREHOUSE_102;
//                     break;
//                 case "WAREHOUSE_103":
//                     warehouseNameEnum = WarehouseName.WAREHOUSE_103;
//                     break;
//                 case "WAREHOUSE_104":
//                     warehouseNameEnum = WarehouseName.WAREHOUSE_104;
//                     break;
//                 case "WAREHOUSE_105":
//                     warehouseNameEnum = WarehouseName.WAREHOUSE_105;
//                     break;
//                 case "WAREHOUSE_106":
//                     warehouseNameEnum = WarehouseName.WAREHOUSE_106;
//                     break;
//                 case "WAREHOUSE_107":
//                     warehouseNameEnum = WarehouseName.WAREHOUSE_107;
//                     break;
//                 case "WAREHOUSE_108":
//                     warehouseNameEnum = WarehouseName.WAREHOUSE_108;
//                     break;
//                 case "WAREHOUSE_109":
//                     warehouseNameEnum = WarehouseName.WAREHOUSE_109;
//                     break;

//             }

//             // warehouse 값이 null이 아닌 경우에만 조건을 추가

//             conditionBuilder.or(warehouse.warehouseName.eq(warehouseNameEnum));
//         }

//         builder.and(conditionBuilder);
//         // tuple.where(conditionBuilder);
//         tuple.where(builder);

//         Sort sort = pageable.getSort();
//         sort.stream().forEach(order -> {
//             Order direction = order.isAscending() ? Order.ASC : Order.DESC;
//             String prop = order.getProperty();

//             PathBuilder<Inventory> orderByExpression = new PathBuilder<>(Inventory.class,
//                     "inventory");
//             tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
//         });

//         // 페이지 처리
//         tuple.offset(pageable.getOffset());
//         tuple.limit(pageable.getPageSize());

//         List<Tuple> result = tuple.fetch();
//         long count = tuple.fetchCount();

//         return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);

//     }

//     @Override
//     public List<Object[]> getInventoryRow(Long inventoryId) {

//         return null;

//     }

// }

package com.example.inventory2.repository.total;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.inventory2.entity.Inventory;
import com.example.inventory2.entity.Product;
import com.example.inventory2.entity.QInventory;
import com.example.inventory2.entity.QProduct;
import com.example.inventory2.entity.QWarehouse;
import com.example.inventory2.status.InventoryStatus;
import com.example.inventory2.status.WarehouseName;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class InventoryProductWarehouseRepositoryImpl extends QuerydslRepositorySupport
        implements InventoryProductWarehouseRepository {

    public InventoryProductWarehouseRepositoryImpl() {
        super(Inventory.class);
    }

    @Override
    public Page<Object[]> getInventoryList(String type, String keyword, String inventorystatusStr,
            String warehouseName, Pageable pageable) {

        log.info("=================================");
        log.info("재고 전체 리스트 {},{},{} {}", type, keyword, inventorystatusStr, warehouseName);

        // Q클래스 나열
        QInventory inventory = QInventory.inventory;
        QProduct product = QProduct.product;
        QWarehouse warehouse = QWarehouse.warehouse;

        JPQLQuery<Inventory> query = from(inventory);
        query.leftJoin(product).on(inventory.product.eq(product));
        query.leftJoin(warehouse).on(inventory.warehouse.eq(warehouse));

        // 해당 순서대로 서비스에서 인덱싱 [] 해서 가져옴
        JPQLQuery<Tuple> tuple = query.select(inventory, product, warehouse);

        // 검색 조건을 추가하기 위한 BooleanBuilder 객체를 생성
        BooleanBuilder builder = new BooleanBuilder();
        // 기본 조건을 추가: 모든 inventoryId가 0보다 큰 조건
        builder.and(inventory.inventoryId.gt(0L));

        // type과 키워드를 이용한 추가 조건
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type != null && !type.isEmpty()) { // **************************
            if (type.contains("productCode")) {
                conditionBuilder.or(product.productCode.containsIgnoreCase(keyword));
            }
            if (type.contains("productName")) {
                conditionBuilder.or(product.productName.containsIgnoreCase(keyword));
            }
        } // **************************

        // inventoryStatusStr 값이 "전체"가 아닌 경우에만 상태 조건을 추가
        if (inventorystatusStr != null && !inventorystatusStr.isEmpty() && !inventorystatusStr.equals("전체")) { // **************************
            InventoryStatus inventoryStatus = null;
            switch (inventorystatusStr) {
                case "교환":
                    inventoryStatus = InventoryStatus.교환;
                    break;
                case "반품":
                    inventoryStatus = InventoryStatus.반품;
                    break;
                case "분실":
                    inventoryStatus = InventoryStatus.분실;
                    break;
                case "입고":
                    inventoryStatus = InventoryStatus.입고;
                    break;
                case "출고":
                    inventoryStatus = InventoryStatus.출고;
                    break;
                case "출고대기":
                    inventoryStatus = InventoryStatus.출고대기;
                    break;
                case "폐기":
                    inventoryStatus = InventoryStatus.폐기;
                    break;
                case "훼손":
                    inventoryStatus = InventoryStatus.훼손;
                    break;
            }

            // inventoryStatus 값이 null이 아닌 경우에만 조건을 추가
            if (inventoryStatus != null) {
                conditionBuilder.and(inventory.inventoryStatus.eq(inventoryStatus));
            }
        } // **************************

        // warehouseName이 null인지 체크 후 조건 추가
        if (warehouseName != null && !warehouseName.isEmpty() && !warehouseName.equals("전체")) { // **************************
            try {
                WarehouseName warehouseNameEnum = WarehouseName.valueOf(warehouseName);
                conditionBuilder.and(warehouse.warehouseName.eq(warehouseNameEnum));
            } catch (IllegalArgumentException e) {

            }
        } // **************************

        builder.and(conditionBuilder);
        tuple.where(builder);

        // 로그 추가: 최종 쿼리 조건을 출력
        log.info("Final Query Conditions: {}", builder);

        // 정렬 추가
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder<Inventory> orderByExpression = new PathBuilder<>(Inventory.class, "inventory");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();
        long count = tuple.fetchCount();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }

    @Override
    public List<Object[]> getInventoryRow(Long inventoryId) {
        return null;
    }
}
