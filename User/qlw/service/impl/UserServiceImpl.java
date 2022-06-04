package com.qlw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qlw.domain.User;
import com.qlw.dao.UserDao;
import com.qlw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author QLW
 * @since 2022-06-02mybatis-plus ServiceImpl
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    UserDao userDao;

    public User getByUsernameUser(String username) {
        LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<User>();
        lambdaQuery.eq(username != null, User::getUsername, username);
        return userDao.selectOne(lambdaQuery);
    }

    public List<User> getAll() {
        return userDao.selectList(null);
    }

    public int saveUser(User user) {
        return userDao.insert(user);
    }

    public int deleteUser(Integer id) {
        return userDao.deleteById(id);
    }

    public int updateUser(String username, String passwd) {
        User user = getByUsernameUser(username);
        user.setPasswd(passwd);
        return userDao.updateById(user);
    }

}
