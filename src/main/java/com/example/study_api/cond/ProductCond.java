package com.example.study_api.cond;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCond {

    private int min;
    private int max;
    @Builder.Default
    private SortType sortType = SortType.PRICE_DESC;


    public enum SortType {
        PRICE_DESC, // 가격 내림차순
        PRICE_ASC, // 가격 오름차순
    }
}
