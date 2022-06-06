package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.Delivery_detailsDao;
import com.example.domain.Delivery_details;
import com.example.domain.Delivery_order;
import com.example.dao.Delivery_orderDao;
import com.example.service.IDelivery_orderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author QLW
 * @since 2022-06-04
 */
@Service
public class Delivery_orderServiceImpl extends ServiceImpl<Delivery_orderDao, Delivery_order> implements IDelivery_orderService {

    @Autowired
    Delivery_orderDao delivery_orderDao;

    public Result getDelivery_orderById(Integer id) {
        Delivery_order delivery_order = delivery_orderDao.selectById(id);
        if (delivery_order != null) {
            return new Result("success", delivery_order);
        } else {
            return new Result("error", "查询失败");
        }
    }


    @Autowired
    Delivery_detailsDao delivery_detailsDao;

    public Result getdelivery_orderListById(Integer id) {
        LambdaQueryWrapper<Delivery_details> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Delivery_details::getOrderId, id);
        return new Result(delivery_detailsDao.selectList(queryWrapper));
    }

    public Result getdelivery_orderPageById(Integer current, Integer size, Integer id) {
//        QueryWrapper<Delivery_details> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("order_id",id);
//        Page<Delivery_details> page = new Page<>(current, size);
//        return new Result(delivery_detailsDao.selectPage(page,queryWrapper));
//        两种写法都可以
        LambdaQueryWrapper<Delivery_details> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Delivery_details::getOrderId, id);
        Page<Delivery_details> page = new Page<>(current, size);
        return new Result(delivery_detailsDao.selectPage(page, queryWrapper));
    }


}
