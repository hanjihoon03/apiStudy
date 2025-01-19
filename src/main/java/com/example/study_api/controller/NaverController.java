package com.example.study_api.controller;

import com.example.study_api.dto.ProductDto;
import com.example.study_api.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/naver")
@RequiredArgsConstructor
public class NaverController {

    private final NaverService naverService;

    @GetMapping("/search")
    public List<ProductDto> searchNaverShopping(@RequestParam String query) {
        return naverService.searchProduct(query);
    }
}
