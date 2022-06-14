package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Article_number;
import com.example.dao.Article_numberDao;
import com.example.domain.Delivery_details;
import com.example.service.IArticle_numberService;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-04
 */
@Service
public class Article_numberServiceImpl implements IArticle_numberService {
    @Autowired
    private Article_numberDao articleNumberDao;

    public boolean save(Article_number articleNumber){
        articleNumber.setArticleNumber();
        return articleNumberDao.insert(articleNumber) > 0;
    }

    public boolean delete(Integer id){
        return articleNumberDao.deleteById(id) > 0;
    }

    public boolean update(Article_number articleNumber){
        Article_number articleNumber1 = articleNumberDao.selectById(articleNumber.getId());
        if(articleNumber.getTradeName() == ""){
            articleNumber.setTradeName(articleNumber1.getTradeName());
        }
        if(articleNumber.getColorNo() == ""){
            articleNumber.setColorNo(articleNumber1.getColorNo());
        }
        if(articleNumber.getSize() == null){
            articleNumber.setSize(articleNumber1.getSize());
        }
        if(articleNumber.getNumber() == 0) {
            articleNumber.setNumber(articleNumber1.getNumber());
        }
        articleNumber.setArticleNumber();
        return articleNumberDao.updateById(articleNumber) > 0;
    }

    public Article_number getById(Integer id){
        return articleNumberDao.selectById(id);
    }

    public List<Article_number> getList(){
        return articleNumberDao.selectList(null);
    }

    public Page<Article_number> getPage(Integer current, Integer size){
        Page<Article_number> page = new Page<>(current, size);
        return articleNumberDao.selectPage(page, null);
    }

    //模糊查询
    public Page<Article_number> get(Article_number articleNumber, Integer current, Integer size){
        Page<Article_number> page = new Page<>(current, size);
        LambdaQueryWrapper<Article_number> lqw = new LambdaQueryWrapper<Article_number>();
        if(articleNumber.getArticleNumber() != null) lqw.like(Article_number::getArticleNumber, articleNumber.getArticleNumber());
        if(articleNumber.getTradeName() != null) lqw.like(Article_number::getTradeName, articleNumber.getTradeName());
        if(articleNumber.getColorNo() != null) lqw.like(Article_number::getColorNo, articleNumber.getColorNo());
        if(articleNumber.getSize() != null) lqw.like(Article_number::getSize, articleNumber.getSize());
        return articleNumberDao.selectPage(page, lqw);
    }
}
