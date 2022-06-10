package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.GrnDao;
import com.example.dao.Warehousing_detailDao;
import com.example.domain.Article_number;
import com.example.domain.Grn;
import com.example.domain.Warehousing_detail;
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

    @Autowired
    private Warehousing_detailDao warehousing_detailDao;

    //删
    public boolean delete(Integer id){
        LambdaQueryWrapper<Warehousing_detail> lqw = new LambdaQueryWrapper<Warehousing_detail>();
        lqw.eq(Warehousing_detail::getGrnId, id);
        warehousing_detailDao.delete(lqw);
        return grnDao.deleteById(id) > 0;
    }

    //改
    public boolean update(Grn grn){
        Grn grn1 = grnDao.selectById(grn.getId());
        if(grn.getWarehouse() == ""){
            grn.setWarehouse(grn1.getWarehouse());
        }
        if(grn.getAgent() == ""){
            grn.setAgent(grn1.getAgent());
        }
        if(grn.getSource() == ""){
            grn.setSource(grn1.getSource());
        }
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

    //分页查询
    public Page<Grn> getPage(Integer current, Integer size){
        Page<Grn> page = new Page<>(current, size);
        return grnDao.selectPage(page, null);
    }

    //查询明细
    public Page<Warehousing_detail> getByIdPage(Integer current, Integer size, Integer id){
        LambdaQueryWrapper<Warehousing_detail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Warehousing_detail::getGrnId, id);
        Page<Warehousing_detail> warehousing_detailPage = new Page<>(current, size);
        return warehousing_detailDao.selectPage(warehousing_detailPage, queryWrapper);
    }
}
