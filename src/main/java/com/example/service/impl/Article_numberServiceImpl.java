package com.example.service.impl;

import com.example.domain.Article_number;
import com.example.dao.Article_numberDao;
import com.example.service.IArticle_numberService;
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
        return articleNumberDao.insert(articleNumber) > 0;
    }

    public boolean delete(Integer id){
        return articleNumberDao.deleteById(id) > 0;
    }

    public boolean update(Article_number articleNumber){
        return articleNumberDao.updateById(articleNumber) > 0;
    }

    public Article_number getById(Integer id){
        return articleNumberDao.selectById(id);
    }

    public List<Article_number> getList(){
        return articleNumberDao.selectList(null);
    }
}
