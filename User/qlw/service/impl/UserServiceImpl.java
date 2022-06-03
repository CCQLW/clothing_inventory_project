package com.qlw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qlw.domain.User;
import com.qlw.dao.UserDao;
import com.qlw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QLW
 * @since 2022-06-02mybatis-plus ServiceImpl
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    UserDao userDao;

    public User getByUsernameUser(String username){
        LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<User>();
        lambdaQuery.eq(User::getUsername,username);
        User user = userDao.selectOne(lambdaQuery);
        return user;
//        List<User> list = getAll();
//        User user = new User();
//        for(int i =0;i<list.size();i++){
//            if(username.equals(list.get(i).getUsername())){
//                BeanUtils.copyProperties(list.get(i),user);
//            }
//        }
//        return user;
    }

    public List<User> getAll(){
        return userDao.selectList(null);
    }


}
