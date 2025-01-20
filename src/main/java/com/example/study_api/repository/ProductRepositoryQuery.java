package com.example.study_api.repository;

import com.example.study_api.cond.ProductCond;
import com.example.study_api.entity.Product;

import java.util.List;

public interface ProductRepositoryQuery {

    /**
     * 지정한 두 가격 사이의 product를 조회하고 정렬 조건을 받음
     * @param productCond 조건(낮은 가격, 높은 가격, 정렬 조건)
     * @return 조회 된 product
     */
    List<Product> searchPriceRangeSort(ProductCond productCond);
}
