package com.example.service.impl;

import com.example.dao.GrnDao;
import com.example.domain.Grn;
import com.example.service.IGrnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-05
 */
@Service
public class GrnServiceImpl implements IGrnService {

    @Autowired
    private GrnDao grnDao;

    //增
    public boolean save(Grn grn){
        grn.setReceiptNumber();
        grn.setStorageTime();
        return grnDao.insert(grn) > 0;
    }

    //删
    public boolean delete(Integer id){
        return grnDao.deleteById(id) > 0;
    }

    //改
    public boolean update(Grn grn){
        return grnDao.updateById(grn) > 0;
    }

    //查
    public Grn getById(Integer id){
        return grnDao.selectById(id);
    }

    //查询所有
    public List<Grn> getList(){
        return grnDao.selectList(null);
    }
}
