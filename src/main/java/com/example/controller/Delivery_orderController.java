package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Delivery_details;
import com.example.domain.Delivery_order;
import com.example.domain.Delivery_order;
import com.example.service.IDelivery_orderService;
import com.example.service.IDelivery_orderService;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author QLW
 * @since 2022-06-04
 */
@RestController
@RequestMapping("/delivery_order")
public class Delivery_orderController {
    @Autowired
    private IDelivery_orderService delivery_orderService;

    @PostMapping
    public Result saveDelivery_order(@RequestBody Delivery_order order) {
        order.setReceiptNumber();
        order.setStorageTime();
        System.out.println(order);
        return new Result(delivery_orderService.save(order));
    }

    @DeleteMapping("/{id}")
    public Result deleteDelivery_order(@PathVariable Integer id) {
        return new Result(delivery_orderService.removeById(id));
    }

    @PutMapping
    public Result updateDelivery_order(@RequestBody Delivery_order order) {
        if ("".equals(order.getWarehouse())) {
            order.setWarehouse(null);
        }
        if ("".equals(order.getAgent())) {
            order.setAgent(null);
        }
        if ("".equals(order.getWhereabouts())) {
            order.setWhereabouts(null);
        }
        return new Result(delivery_orderService.updateById(order));
    }

    @GetMapping
    public Result getAll() {
        return new Result("success", delivery_orderService.list());
    }

    @GetMapping("/{id}")
    public Result getDelivery_orderById(@PathVariable Integer id) {
        return delivery_orderService.getDelivery_orderById(id);
    }

    @GetMapping("/pageorder")
    public Result getOrderPage(Integer current, Integer size) {
        IPage<Delivery_order> page = new Page<>(current, size);
        return new Result(delivery_orderService.page(page));
    }

    @GetMapping("/fuzzy")
    public Result fuzzy(Delivery_order order, Integer page, Integer size) {
        LambdaQueryWrapper<Delivery_order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(order.getReceiptNumber() != null, Delivery_order::getReceiptNumber, order.getReceiptNumber());
        queryWrapper.like(order.getWarehouse() != null, Delivery_order::getWarehouse, order.getWarehouse());
//        queryWrapper.like(order.getStorageTime()!=null,Delivery_order::getStorageTime, order.getStorageTime());
//        queryWrapper.like(order.getStorageTime()!=null,Delivery_order::getStorageTime, order.getStorageTime());
        queryWrapper.like(order.getAgent() != null, Delivery_order::getAgent, order.getAgent());
        queryWrapper.like(order.getWhereabouts() != null, Delivery_order::getWhereabouts, order.getWhereabouts());
        Page<Delivery_order> pages = new Page<>(page, size);
        return new Result("success", delivery_orderService.page(pages, queryWrapper));
    }

    @GetMapping("/page")
    public Result getPage(Delivery_details details, Integer page, Integer sizep, Integer id) {
        LambdaQueryWrapper<Delivery_details> queryWrapper = new LambdaQueryWrapper<>();
        return delivery_orderService.getdelivery_orderPageById(page, sizep, id, details);
    }
}

