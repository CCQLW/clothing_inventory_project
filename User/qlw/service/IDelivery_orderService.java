package com.qlw.service;

import com.qlw.domain.Delivery_order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qlw.utils.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author QLW
 * @since 2022-06-04
 */
public interface IDelivery_orderService extends IService<Delivery_order> {

    Result getDelivery_orderById(Integer id);
    public Result getdelivery_orderListById(Integer id);
    public Result getdelivery_orderPageById(Integer current, Integer size, Integer id);
}
