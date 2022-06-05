package com.qlw.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlw.domain.Delivery_order;
import com.qlw.service.IDelivery_orderService;
import com.qlw.service.IDelivery_orderService;
import com.qlw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result saveDelivery_order(@RequestBody Delivery_order details) {
        return new Result(delivery_orderService.save(details));
    }

    @DeleteMapping("/{id}")
    public Result deleteDelivery_order(@PathVariable Integer id) {
        return new Result(delivery_orderService.removeById(id));
    }

    @PutMapping
    public Result updateDelivery_order(@RequestBody Delivery_order details) {
        return new Result(delivery_orderService.updateById(details));
    }

    @GetMapping
    public Result getAll() {
        return new Result("success", delivery_orderService.list());
    }

    @GetMapping("/{id}")
    public Result getDelivery_orderById(@PathVariable Integer id) {
        return delivery_orderService.getDelivery_orderById(id);

    }

    @GetMapping("/page")
    public Result getPage(Integer current, Integer size,Integer id) {
       return delivery_orderService.getdelivery_orderPageById(current, size, id);
//        IPage<Delivery_order> page = new Page<Delivery_order>(current, size);
//        return new Result(delivery_orderService.page(page, null));
    }
}

