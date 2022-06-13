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
        Warehousing_detail warehousingDetail = warehousing_detailDao.selectById(id);
        Article_number articleNumber = articleNumberDao.selectById(warehousingDetail.getArticleId());
        if(articleNumber.getNumber() - warehousingDetail.getNumber() >= 0){
            articleNumber.setNumber(articleNumber.getNumber() - warehousingDetail.getNumber());
        }
        articleNumberDao.updateById(articleNumber);
        return warehousing_detailDao.deleteById(id) > 0;
    }

    //改
    public boolean update(Warehousing_detail warehousing_detail){
        Warehousing_detail warehousing_detail1 = warehousing_detailDao.selectById(warehousing_detail.getId());//旧的明细
        Article_number article_number;
        if(warehousing_detail.getArticleId() == null){
            article_number = articleNumberDao.selectById(warehousing_detail1.getArticleId());
            Integer updateNumber = 0;
            if(warehousing_detail.getNumber() != null) {
                updateNumber = warehousing_detail.getNumber() - warehousing_detail1.getNumber();
            }
            if(article_number.getNumber() + updateNumber >= 0){
                article_number.setNumber(article_number.getNumber() + updateNumber);
            }
            else{
                return false;
            }
            articleNumberDao.updateById(article_number);
        }
        else{
            article_number = articleNumberDao.selectById(warehousing_detail.getArticleId());
            Article_number article_number1 = articleNumberDao.selectById(warehousing_detail1.getArticleId());
            if(article_number1.getNumber() - warehousing_detail1.getNumber() >= 0){
                article_number1.setNumber(article_number1.getNumber() - warehousing_detail1.getNumber());
            }
            else{
                return false;
            }
            articleNumberDao.updateById(article_number1);
            Integer updateNumber = 0;
            if(warehousing_detail.getNumber() == null){
                updateNumber = warehousing_detail1.getNumber();
            }
            else{
                updateNumber = warehousing_detail.getNumber();
            }
            article_number.setNumber(updateNumber);
            articleNumberDao.updateById(article_number);
        }
        warehousing_detail.setArticle(article_number);
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

    //模糊查询
    public Page<Warehousing_detail> get(Warehousing_detail warehousing_detail, Integer current , Integer size){
        Page<Warehousing_detail> page = new Page<>(current, size);
        LambdaQueryWrapper<Warehousing_detail> lqw = new LambdaQueryWrapper<Warehousing_detail>();
        if(warehousing_detail.getArticleNumber() != null) lqw.like(Warehousing_detail::getArticleNumber, warehousing_detail.getArticleNumber());
        if(warehousing_detail.getTradeName() != null) lqw.like(Warehousing_detail::getTradeName, warehousing_detail.getTradeName());
        if(warehousing_detail.getColorNo() != null) lqw.like(Warehousing_detail::getColorNo, warehousing_detail.getColorNo());
        if(warehousing_detail.getSize() != null) lqw.like(Warehousing_detail::getSize, warehousing_detail.getSize());
        lqw.like(Warehousing_detail::getGrnId, warehousing_detail.getGrnId());
        return warehousing_detailDao.selectPage(page, lqw);
    }
}
