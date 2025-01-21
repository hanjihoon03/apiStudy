package com.example.study_api.repository;

import com.example.study_api.cond.ProductCond;
import com.example.study_api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryQuery {

    /**
     * 지정한 두 가격 사이의 product를 조회하고 정렬 조건을 받음
     * @param productCond 조건(낮은 가격, 높은 가격, 정렬 조건)
     * @return 조회 된 product
     */
    List<Product> searchPriceRangeSort(ProductCond productCond);

    /**
     * 지정한 두 가격 사이의 product를 조회하고 정렬 조건을 받아 페이징해 조회
     * @param productCond 조건
     * @param pageable 페이징 조건, 페이지 번호화 개수
     * @return 페이징된 product
     */
    Page<Product> searchPriceRangeSortPage(ProductCond productCond, Pageable pageable);
}
