package com.example.study_api.service;

import com.example.study_api.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NaverService {

    private final RestTemplate restTemplate;

    public List<ProductDto> searchProduct(String item) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/shop.json")
                .queryParam("display", 30)
                .queryParam("query", item)
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "oO4CGkiDwQoXx2XLQx0e")
                .header("X-Naver-Client-Secret", "8ehcw0jCDX")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        return fromJSONtoProduct(responseEntity.getBody());
    }

    public List<ProductDto> fromJSONtoProduct(String responseEntityBody) {
        JSONObject jsonObject = new JSONObject(responseEntityBody);
        JSONArray items = jsonObject.getJSONArray("items");
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Object product : items) {
            ProductDto productDto = new ProductDto((JSONObject) product);
            productDtoList.add(productDto);
        }

        return productDtoList;
    }
}
