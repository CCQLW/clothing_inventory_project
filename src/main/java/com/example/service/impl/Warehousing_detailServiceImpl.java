package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.Article_numberDao;
import com.example.domain.Article_number;
import com.example.domain.Delivery_details;
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
        Article_number article_number = articleNumberDao.selectById(warehousing_detail.getArticleId());
        warehousing_detail.setArticle(article_number);
        article_number.setNumber(article_number.getNumber() + warehousing_detail.getNumber());
        articleNumberDao.updateById(article_number);
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

    //分页查询
    public Page<Warehousing_detail> getPage(Integer current, Integer size){
        Page<Warehousing_detail> warehousing_detailPage = new Page<>(current, size);
        return warehousing_detailDao.selectPage(warehousing_detailPage, null);
    }

    //根据入库单序号分页查询
    public Page<Warehousing_detail> getByGrnIdPage(Integer current, Integer size, Integer id){
        LambdaQueryWrapper<Warehousing_detail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Warehousing_detail::getGrnId, id);
        Page<Warehousing_detail> warehousing_detailPage = new Page<>(current, size);
        return warehousing_detailDao.selectPage(warehousing_detailPage, queryWrapper);
    }
}
