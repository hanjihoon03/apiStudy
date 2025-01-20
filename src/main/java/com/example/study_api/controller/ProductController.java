package com.example.study_api.controller;

import com.example.study_api.cond.ProductCond;
import com.example.study_api.dto.ProductDto;
import com.example.study_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public List<ProductDto> searchPriceRangeSort(@RequestParam int min,
                                                 @RequestParam int max,
                                                 @RequestParam String sort) {
        ProductCond.SortType sortType = productService.changeSort(sort);
        ProductCond productCond = new ProductCond(min, max, sortType);

        return productService.searchPriceRangeSort(productCond);

    }

}
