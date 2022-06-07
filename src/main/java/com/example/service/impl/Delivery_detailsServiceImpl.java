package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dao.Article_numberDao;
import com.example.domain.Article_number;
import com.example.domain.Delivery_details;
import com.example.dao.Delivery_detailsDao;
import com.example.service.IDelivery_detailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QLW
 * @since 2022-06-04
 */
@Service
public class Delivery_detailsServiceImpl extends ServiceImpl<Delivery_detailsDao, Delivery_details> implements IDelivery_detailsService {
//    @Autowired
//    Delivery_detailsDao delivery_detailsDao;
//    public Delivery_details getDelivery_orderByDelivery_detailsId(Integer id) {
//        Delivery_details delivery_details = delivery_detailsDao.selectById(id);
//        Integer delivery_orderId = delivery_details.getOrderId();
//        return  delivery_detailsDao.selectById(delivery_orderId);
//    }

//    Article_numberDao article_numberDao;
//    public Delivery_details getArticle_numberByDelivery_detailsId(Integer id) {
//        Article_number article_number = article_numberDao.selectById(id);
//        Integer article_numberId = article_number.getId()
//        return  article_numberDao.selectById(article_numberId);
//    }

    @Autowired
    Delivery_detailsDao delivery_detailsDao;
    public List<Delivery_details> getArticle_numberListByDelivery_detailsId(Integer article_numberId) {
        LambdaQueryWrapper<Delivery_details> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Delivery_details::getArticleId, article_numberId);
        return delivery_detailsDao.selectList(queryWrapper);
    }
}
