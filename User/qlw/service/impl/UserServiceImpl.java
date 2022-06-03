package com.qlw.service.impl;

import com.qlw.domain.User;
import com.qlw.dao.UserDao;
import com.qlw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QLW
 * @since 2022-06-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    UserDao userDao;

//    public List<User> getall(){
//        return userDao.selectList(null);
//    }
//
//    public User getById(@PathVariable Integer id){
//        return userDao.selectById(id);
//    }
}
