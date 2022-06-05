package com.example.controller;


import com.example.domain.Article_number;
import com.example.service.IArticle_numberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-04
 */
@RestController
@RequestMapping("/article_number")
public class Article_numberController {
    @Autowired
    private IArticle_numberService iArticle_numberService;

    @PostMapping
    public Result save(@RequestBody Article_number articleNumber) {
        boolean flag = iArticle_numberService.save(articleNumber);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PutMapping
    public Result update(@RequestBody Article_number articleNumber) {
        boolean flag = iArticle_numberService.update(articleNumber);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = iArticle_numberService.delete(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Article_number articleNumber = iArticle_numberService.getById(id);
        Integer code = articleNumber != null ? Code.GET_OK : Code.GET_ERR;
        String msg = articleNumber != null ? "" : "数据查询失败，请重试！";
        return new Result(code,articleNumber,msg);
    }

    @GetMapping
    public Result getAll() {
        List<Article_number> articleNumberList = iArticle_numberService.getList();
        Integer code = articleNumberList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = articleNumberList != null ? "" : "数据查询失败，请重试！";
        return new Result(code,articleNumberList,msg);
    }
}

