package com.example.study_api;

import com.example.study_api.entity.User;
import com.example.study_api.repository.ProductRepository;
import com.example.study_api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TestMappingTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    void test1() {
        User user = userRepository.findById(1L).orElseThrow(NullPointerException::new);

        System.out.println("user.getProducts() = " + user.getProducts().size());
    }
}
