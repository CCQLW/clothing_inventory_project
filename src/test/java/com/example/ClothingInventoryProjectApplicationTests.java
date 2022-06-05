package com.example;

import com.example.dao.Article_numberDao;
import com.example.dao.GrnDao;
import com.example.dao.UserDao;
import com.example.domain.Article_number;
import com.example.domain.Grn;
import com.example.domain.User;
import com.example.service.IArticle_numberService;
import com.example.service.IGrnService;
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
        articleNumber.setColorNo("red");
        articleNumber.setSize(170);
        iArticle_numberService.save(articleNumber);
    }

    @Test
    void testUpdate(){
        Article_number articleNumber = new Article_number();
        articleNumber.setColorNo("blue");
        articleNumber.setId(8);
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

    @Autowired
    private IGrnService iGrnService;

    @Test
    void testGrnSave(){
        Grn grn = new Grn();
        grn.setWarehouse("第二仓库");
        grn.setAgent("张三");
        grn.setSource("第二分公司");
        iGrnService.save(grn);
    }

    @Test
    void testGrnUpdate(){
        Grn grn = new Grn();
        grn.setId(2);
        grn.setAgent("李四");
        iGrnService.update(grn);
    }

    @Test
    void testGrnDelete(){
        iGrnService.delete(3);
    }

    @Test
    void testGrnGetById(){
        Grn grn = iGrnService.getById(2);
        System.out.println(grn);
    }

    @Test
    void testGrnGetList(){
        List<Grn> list = iGrnService.getList();
        System.out.println(list);
    }

}
