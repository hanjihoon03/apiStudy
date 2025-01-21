package com.example.study_api.service;

import com.example.study_api.cond.ProductCond;
import com.example.study_api.dto.ProductDto;
import com.example.study_api.entity.Product;
import com.example.study_api.entity.User;
import com.example.study_api.repository.ProductRepository;
import com.example.study_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public void saveProduct(List<ProductDto> productDtoList) {
        List<Product> products = new ArrayList<>();
        User user = userRepository.findById(1L).orElse(null);

        for (ProductDto productDto : productDtoList) {
            products.add(new Product(productDto, user));
        }
        productRepository.saveAll(products);
    }


    public List<ProductDto> searchPriceRangeSort(ProductCond productCond) {

        List<ProductDto> productDtoList = new ArrayList<>();

        List<Product> products = productRepository.searchPriceRangeSort(productCond);

        for (Product product : products) {
            productDtoList.add(new ProductDto(product));
        }

        return productDtoList;
    }

    public ProductCond.SortType changeSort(String sort) {
        if (sort.equals("asc")) {
            return ProductCond.SortType.PRICE_ASC;
        } else {
            return ProductCond.SortType.PRICE_DESC;
        }
    }

    public Page<ProductDto> searchPriceRangeSortPage(ProductCond productCond, Pageable pageable) {
        return productRepository.searchPriceRangeSortPage(productCond, pageable).map(ProductDto::new);
    }
}
