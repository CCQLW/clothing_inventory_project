package com.example;

import com.example.dao.Article_numberDao;
import com.example.dao.UserDao;
import com.example.domain.Article_number;
import com.example.domain.User;
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
        articleNumber.setTradeName("T恤");
        articleNumber.setArticleNumber("bbbbb");
        articleNumber.setColorNo("bbbbb");
        articleNumber.setSize(170);
        iArticle_numberService.save(articleNumber);
    }

    @Test
    void testUpdate(){
        Article_number articleNumber = new Article_number();
        articleNumber.setArticleNumber("DCT-blue-173");
        articleNumber.setColorNo("blue");
        articleNumber.setId(5);
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
    }

    @Test
    void testGetList(){
        List<Article_number> list = iArticle_numberService.getList();
        System.out.println(list);
    }

    @Autowired
    private UserDao userDao;

    @Test
    void testUserSave(){
        User user = new User();
        user.setUsername("bbbb");
        user.setPasswd("bbbb");
        user.setAuthority(12);
        userDao.insert(user);
    }

}
