package com.example.service.impl;

import com.example.dao.Article_numberDao;
import com.example.domain.Grn;
import com.example.domain.Warehousing_detail;
import com.example.dao.Warehousing_detailDao;
import com.example.service.IWarehousing_detailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-05
 */
@Service
public class Warehousing_detailServiceImpl implements IWarehousing_detailService {

    @Autowired
    private Warehousing_detailDao warehousing_detailDao;

    @Autowired
    private Article_numberDao articleNumberDao;

    //增
    public boolean save(Warehousing_detail warehousing_detail){
        warehousing_detail.setArticle(articleNumberDao.selectById(warehousing_detail.getArticleId()));
        return warehousing_detailDao.insert(warehousing_detail) > 0;
    }

    //删
    public boolean delete(Integer id){
        return warehousing_detailDao.deleteById(id) > 0;
    }

    //改
    public boolean update(Warehousing_detail warehousing_detail){
        warehousing_detail.setArticle(articleNumberDao.selectById(warehousing_detail.getArticleId()));
        return warehousing_detailDao.updateById(warehousing_detail) > 0;
    }

    //查
    public Warehousing_detail getById(Integer id){
        return warehousing_detailDao.selectById(id);
    }

    //查询所有
    public List<Warehousing_detail> getList(){
        return warehousing_detailDao.selectList(null);
    }
}
