package com.example.service;

import com.example.domain.Delivery_details;
import com.example.domain.Delivery_order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.utils.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author QLW
 * @since 2022-06-04
 */
public interface IDelivery_orderService extends IService<Delivery_order> {

    Result getDelivery_orderById(Integer id);

    Result getdelivery_orderListById(Integer id);

    Result getdelivery_orderPageById(Integer current, Integer size, Integer id, Delivery_details details);
}
