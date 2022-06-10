package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Article_number;
import com.example.domain.Grn;
import com.example.domain.Warehousing_detail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-05
 */
public interface IGrnService{
    //增
    public boolean save(Grn grn);

    //删
    public boolean delete(Integer id);

    //改
    public boolean update(Grn grn);

    //查
    public Grn getById(Integer id);

    //查询所有
    public List<Grn> getList();

    //分页查询
    public Page<Grn> getPage(Integer current, Integer size);

    //模糊查询
    public Page<Grn> get(Grn grn, Integer current, Integer size);
}
