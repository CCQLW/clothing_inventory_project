package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @PostMapping("/save")
    public Result save(@RequestBody Article_number articleNumber) {
        boolean flag = iArticle_numberService.save(articleNumber);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Article_number articleNumber) {
        boolean flag = iArticle_numberService.update(articleNumber);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = iArticle_numberService.delete(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Integer id) {
        Article_number articleNumber = iArticle_numberService.getById(id);
        Integer code = articleNumber != null ? Code.GET_OK : Code.GET_ERR;
        String msg = articleNumber != null ? "" : "数据查询失败，请重试！";
        return new Result(code,articleNumber,msg);
    }

    @GetMapping("/getAll")
    public Result getAll() {
        List<Article_number> articleNumberList = iArticle_numberService.getList();
        Integer code = articleNumberList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = articleNumberList != null ? "" : "数据查询失败，请重试！";
        return new Result(code,articleNumberList,msg);
    }

    @GetMapping("/getPage")
    public Result getPage(Integer current, Integer size){
        Page<Article_number> article_numberPage = iArticle_numberService.getPage(current, size);
        Integer code = article_numberPage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = article_numberPage != null ? "" : "数据查询失败，请重试！";
        return new Result(code, article_numberPage, msg);
    }

    @GetMapping("/get")
    public Result get(Integer current, String articleNumber, String tradeName, String colorNo, Integer size){
        Article_number article_number = new Article_number();
        article_number.setArticleNumber(articleNumber);
        article_number.setTradeName(tradeName);
        article_number.setColorNo(colorNo);
        article_number.setSize(size);
        Page<Article_number> article_numberPage = iArticle_numberService.get(article_number, current, 5);
        Integer code = article_numberPage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = article_numberPage != null ? "" : "数据查询失败，请重试！";
        return new Result(code, article_numberPage, msg);
    }

    @GetMapping("/getNumberById/{id}")
    public Result getNumberById(@PathVariable Integer id){
        Article_number article_number = iArticle_numberService.getById(id);
        Integer code = article_number != null ? Code.GET_OK : Code.GET_ERR;
        String msg = article_number != null ? "" : "数据查询失败，请重试！";
        return new Result(code, article_number.getNumber(), msg);
    }
}

