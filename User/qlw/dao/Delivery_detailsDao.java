package com.qlw.dao;

import com.qlw.domain.Delivery_details;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qlw.utils.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author QLW
 * @since 2022-06-04
 */
@Mapper
public interface Delivery_detailsDao extends BaseMapper<Delivery_details> {
}
