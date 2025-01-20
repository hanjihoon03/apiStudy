package com.example.study_api.entity;

import com.example.study_api.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    private String link;
    private String image;
    private String brand;
    private int lprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Product(ProductDto productDto, User user) {
        this.title = productDto.getTitle();
        this.link = productDto.getLink();
        this.image = productDto.getImage();
        this.brand = productDto.getBrand();
        this.lprice = productDto.getLprice();
        this.user = user;
    }

    public Product(ProductDto productDto) {
        this.title = productDto.getTitle();
        this.link = productDto.getLink();
        this.image = productDto.getImage();
        this.brand = productDto.getBrand();
        this.lprice = productDto.getLprice();
    }
}
