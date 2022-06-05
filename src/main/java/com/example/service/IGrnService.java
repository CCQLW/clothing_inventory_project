package com.example.service;

import com.example.domain.Article_number;
import com.example.domain.Grn;

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
}
