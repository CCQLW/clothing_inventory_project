package com.qlw.dao;

import com.qlw.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author QLW
 * @since 2022-06-02
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
