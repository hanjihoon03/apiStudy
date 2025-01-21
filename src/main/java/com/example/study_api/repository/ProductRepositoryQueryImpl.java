package com.example.study_api.repository;

import com.example.study_api.cond.ProductCond;
import com.example.study_api.entity.Product;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.classgraph.TypeArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.example.study_api.entity.QProduct.*;

@RequiredArgsConstructor
public class ProductRepositoryQueryImpl implements ProductRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> searchPriceRangeSort(ProductCond productCond) {

        return getProductJPAQuery(productCond)
                .fetch();
    }

    @Override
    public Page<Product> searchPriceRangeSortPage(ProductCond productCond, Pageable pageable) {
        JPAQuery<Product> query = getProductJPAQuery(productCond)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Product> productList = query.fetch();

        long productListSize = getCountProduct(productCond).fetch().getFirst();

        return PageableExecutionUtils.getPage(productList, pageable, () -> productListSize);
    }

    private JPAQuery<Product> getProductJPAQuery(ProductCond productCond) {
        return jpaQueryFactory.select(product)
                .from(product)
                .where(getBetween(productCond))
                .orderBy(createProductSpecifier(productCond.getSortType()));
    }

    private JPAQuery<Long> getCountProduct(ProductCond productCond) {
        return jpaQueryFactory.select(Wildcard.count)
                .from(product)
                .where(getBetween(productCond))
                .orderBy(createProductSpecifier(productCond.getSortType()));
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
