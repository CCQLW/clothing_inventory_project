package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Article_number;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-04
 */
public interface IArticle_numberService{
    //增
    public boolean save(Article_number articleNumber);

    //删
    public boolean delete(Integer id);

    //改
    public boolean update(Article_number articleNumber);

    //查
    public Article_number getById(Integer id);

    //查询所有
    public List<Article_number> getList();

    //分页查询
    public Page<Article_number> getPage(Integer current, Integer size);
}
