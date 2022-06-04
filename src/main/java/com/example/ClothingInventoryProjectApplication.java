package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = "com.Article.dao")
public class ClothingInventoryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothingInventoryProjectApplication.class, args);
    }

}
