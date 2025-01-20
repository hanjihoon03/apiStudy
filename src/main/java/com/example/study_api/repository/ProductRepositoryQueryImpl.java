package com.example.study_api.repository;

import com.example.study_api.cond.ProductCond;
import com.example.study_api.entity.Product;
import com.example.study_api.entity.QProduct;
import com.example.study_api.entity.QUser;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.study_api.entity.QProduct.*;
import static com.example.study_api.entity.QUser.*;

@RequiredArgsConstructor
public class ProductRepositoryQueryImpl implements ProductRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> searchPriceRangeSort(ProductCond productCond) {

        return jpaQueryFactory.select(product)
                .from(product)
                .where(getBetween(productCond))
                .orderBy(createProductSpecifier(productCond.getSortType()))
                .fetch();
    }

    private BooleanExpression getBetween(ProductCond productCond) {
        return product.lprice.between(productCond.getMin(), productCond.getMax());
    }

    private OrderSpecifier createProductSpecifier(ProductCond.SortType sortType) {
        return switch (sortType) {
            case PRICE_ASC -> product.lprice.asc();
            case PRICE_DESC -> product.lprice.desc();
        };

    }
}
