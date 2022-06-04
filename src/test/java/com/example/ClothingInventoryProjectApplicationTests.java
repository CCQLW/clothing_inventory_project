package com.example;

import com.example.dao.Article_numberDao;
import com.example.domain.Article_number;
import com.example.service.IArticle_numberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ClothingInventoryProjectApplicationTests {

    @Autowired
    private IArticle_numberService iArticle_numberService;

    @Test
    void testSave(){
        Article_number articleNumber = new Article_number();
        articleNumber.setTrade_name("T恤");
        articleNumber.setArticle_number("bbbbb");
        articleNumber.setColor_no("bbbbb");
        articleNumber.setSize(170);
        iArticle_numberService.save(articleNumber);
    }

    @Test
    void testUpdate(){
        Article_number articleNumber = new Article_number();
        articleNumber.setArticle_number("DCT-blue-173");
        articleNumber.setColor_no("blue");
        iArticle_numberService.update(articleNumber);
    }

    @Test
    void testDelete(){
        iArticle_numberService.delete(4);
    }

    @Test
    void testGetById(){
        Article_number articleNumber = iArticle_numberService.getById(3);
        System.out.println(articleNumber);
        System.out.println(articleNumber.getArticle_number());
    }

    @Test
    void testGetList(){
        List<Article_number> list = iArticle_numberService.getList();
        System.out.println(list);
    }

}
