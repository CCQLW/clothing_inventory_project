package com.example.service.impl;

import com.example.domain.Delivery_details;
import com.example.dao.Delivery_detailsDao;
import com.example.service.IDelivery_detailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    Delivery_detailsDao delivery_detailsDao;
    public Delivery_details getDelivery_orderByDelivery_detailsId(Integer id) {
        Delivery_details delivery_details = delivery_detailsDao.selectById(id);
        Integer delivery_orderId = delivery_details.getOrderId();
        return  delivery_detailsDao.selectById(delivery_orderId);
    }

}
