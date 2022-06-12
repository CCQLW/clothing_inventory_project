package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Article_number;
import com.example.domain.Grn;
import com.example.domain.Warehousing_detail;
import com.example.service.IArticle_numberService;
import com.example.service.IGrnService;
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
@RequestMapping("/grn")
public class GrnController {
    @Autowired
    private IGrnService iGrnService;

    @PostMapping("save")
    public Result save(@RequestBody Grn grn) {
        boolean flag = iGrnService.save(grn);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PutMapping("update")
    public Result update(@RequestBody Grn grn) {
        boolean flag = iGrnService.update(grn);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = iGrnService.delete(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id) {
        Grn grn = iGrnService.getById(id);
        Integer code = grn != null ? Code.GET_OK : Code.GET_ERR;
        String msg = grn != null ? "" : "数据查询失败，请重试！";
        return new Result(code,grn,msg);
    }

    @GetMapping("getList")
    public Result getAll() {
        List<Grn> grnList = iGrnService.getList();
        Integer code = grnList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = grnList != null ? "" : "数据查询失败，请重试！";
        return new Result(code,grnList,msg);
    }

    @GetMapping("getPage")
    public Result getPage(Integer current, Integer size){
        Page<Grn> grnPage = iGrnService.getPage(current, size);
        Integer code = grnPage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = grnPage != null ? "" : "数据查询失败，请重试！";
        return new Result(code, grnPage, msg);
    }

    @GetMapping("/get")
    public Result get(Integer current, String receiptNumber, String warehouse, String agent, String source){
        Grn grn = new Grn();
        grn.setReceiptNumber(receiptNumber);
        grn.setWarehouse(warehouse);
        grn.setAgent(agent);
        grn.setSource(source);
        Page<Grn> grnPage = iGrnService.get(grn, current, 5);
        Integer code = grnPage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = grnPage != null ? "" : "数据查询失败，请重试！";
        return new Result(code, grnPage, msg);
    }
}

