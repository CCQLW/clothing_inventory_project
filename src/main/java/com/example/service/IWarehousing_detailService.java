package com.example.service;

import com.example.domain.Grn;
import com.example.domain.Warehousing_detail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-05
 */
public interface IWarehousing_detailService{
    //增
    public boolean save(Warehousing_detail warehousing_detail);

    //删
    public boolean delete(Integer id);

    //改
    public boolean update(Warehousing_detail warehousing_detail);

    //查
    public Warehousing_detail getById(Integer id);

    //查询所有
    public List<Warehousing_detail> getList();
}
