package com.southwind.mmall.service;

import com.southwind.mmall.mapper.ProductCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryServiceTest {
    @Autowired
    private ProductCategoryMapper mapper;
    @Test
    void test(){
        mapper.selectList(null).forEach(System.out::println);
    }
}