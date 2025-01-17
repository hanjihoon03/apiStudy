package com.example.study_api.dto;

import com.example.study_api.entity.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String title;
    private String link;
    private String image;
    private String brand;
    private int lprice;
    private int hprice;

    public ProductDto(Product product) {
        this.title = product.getTitle();
        this.link = product.getLink();
        this.image = product.getImage();
        this.brand = product.getBrand();
        this.lprice = product.getLprice();
        this.hprice = product.getHprice();
    }

    public ProductDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.brand = itemJson.getString("brand");
        this.lprice = itemJson.getInt("lprice");
        this.hprice = itemJson.getInt("hprice");
    }
}
