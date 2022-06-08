package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Grn;
import com.example.domain.Warehousing_detail;
import com.example.service.IGrnService;
import com.example.service.IWarehousing_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-05
 */
@RestController
@RequestMapping("/warehousing_detail")
public class Warehousing_detailController {
    @Autowired
    private IWarehousing_detailService iWarehousing_detailService;

    @PostMapping("/save")
    public Result save(@RequestBody Warehousing_detail warehousing_detail) {
        boolean flag = iWarehousing_detailService.save(warehousing_detail);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Warehousing_detail warehousing_detail) {
        boolean flag = iWarehousing_detailService.update(warehousing_detail);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = iWarehousing_detailService.delete(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id) {
        Warehousing_detail warehousing_detail = iWarehousing_detailService.getById(id);
        Integer code = warehousing_detail != null ? Code.GET_OK : Code.GET_ERR;
        String msg = warehousing_detail != null ? "" : "数据查询失败，请重试！";
        return new Result(code,warehousing_detail,msg);
    }

    @GetMapping("getAll")
    public Result getAll() {
        List<Warehousing_detail> warehousingDetailList = iWarehousing_detailService.getList();
        Integer code = warehousingDetailList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = warehousingDetailList != null ? "" : "数据查询失败，请重试！";
        return new Result(code,warehousingDetailList,msg);
    }

    @GetMapping("getPage")
    public Result getPage(Integer page, Integer size){
        Page<Warehousing_detail> warehousing_detailPage = iWarehousing_detailService.getPage(page, size);
        Integer code = warehousing_detailPage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = warehousing_detailPage != null ? "" : "数据查询失败，请重试！";
        return new Result(code, warehousing_detailPage, msg);
    }

    @GetMapping("getByGrnIdPage")
    public Result getByGrnIdPage(Integer page, Integer size, Integer id){
        Page<Warehousing_detail> warehousing_detailPage = iWarehousing_detailService.getByGrnIdPage(page, size, id);
        Integer code = warehousing_detailPage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = warehousing_detailPage != null ? "" : "数据查询失败，请重试！";
        return new Result(code, warehousing_detailPage, msg);
    }
}

