package com.qlw.controller;


import com.qlw.domain.Delivery_details;
import com.qlw.service.IDelivery_detailsService;
import com.qlw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author QLW
 * @since 2022-06-04
 */
@RestController
@RequestMapping("/delivery_details")
public class Delivery_detailsController {

    @Autowired
    private IDelivery_detailsService delivery_detailsService;

    @PostMapping
    public Result saveDelivery_details(@RequestBody Delivery_details details){
        return new Result(delivery_detailsService.save(details));
    }

    @DeleteMapping("/{id}")
    public Result deleteDelivery_details(@PathVariable Integer id){
        return new Result(delivery_detailsService.removeById(id));
    }

    @PutMapping
    public Result changeNumber(@RequestBody Delivery_details details){
        return new Result(delivery_detailsService.updateById(details));
    }

    @GetMapping
    public Result getAll(){
        return new Result("success",delivery_detailsService.list());
    }

    @GetMapping("/{id}")
    public Result getDelivery_detailsById(@PathVariable Integer id){
        return new Result("success",delivery_detailsService.getById(id));
    }
}

