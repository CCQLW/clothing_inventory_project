package com.example.controller;


import com.example.domain.Article_number;
import com.example.domain.Grn;
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

    @PostMapping
    public Result save(@RequestBody Grn grn) {
        boolean flag = iGrnService.save(grn);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PutMapping
    public Result update(@RequestBody Grn grn) {
        boolean flag = iGrnService.update(grn);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = iGrnService.delete(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Grn grn = iGrnService.getById(id);
        Integer code = grn != null ? Code.GET_OK : Code.GET_ERR;
        String msg = grn != null ? "" : "数据查询失败，请重试！";
        return new Result(code,grn,msg);
    }

    @GetMapping
    public Result getAll() {
        List<Grn> grnList = iGrnService.getList();
        Integer code = grnList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = grnList != null ? "" : "数据查询失败，请重试！";
        return new Result(code,grnList,msg);
    }
}

