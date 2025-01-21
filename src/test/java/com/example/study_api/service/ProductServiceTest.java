package com.example.study_api.service;

import com.example.study_api.cond.ProductCond;
import com.example.study_api.dto.ProductDto;
import com.example.study_api.entity.Product;
import com.example.study_api.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("받은 파라미터를 조건으로 낮은 가격과 높은 가격 사이의 product를 정렬해 조회")
    void test1() {
        //given
        String sort = "desc";
        int min = 10000;
        int max = 30000;

        //when
        ProductCond.SortType sortType = productService.changeSort(sort);
        ProductCond productCond = new ProductCond(min, max, sortType);

        List<ProductDto> productDtoList = productService.searchPriceRangeSort(productCond);

        //then
        assertThat(productDtoList.size()).isEqualTo(22);
        assertThat(productDtoList.getFirst().getLprice()).isEqualTo(23400);

    }
    @Test
    @DisplayName("받은 파라미터를 조건으로 낮은 가격과 높은 가격 사이의 product를 정렬해 조회하고 페이징")
    void test2() {
        //given
        String sort = "desc";
        int min = 10000;
        int max = 30000;
        int offset = 1;
        int limit = 5;

        //when
        ProductCond.SortType sortType = productService.changeSort(sort);
        ProductCond productCond = new ProductCond(min, max, sortType);
        PageRequest pageRequest = PageRequest.of(offset - 1, limit);

        Page<Product> products = productRepository.searchPriceRangeSortPage(productCond, pageRequest);

        //then
        assertThat(products.getSize()).isEqualTo(5);
        assertThat(products.getTotalPages()).isEqualTo(5);

    }

}