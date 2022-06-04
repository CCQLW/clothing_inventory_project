package com.qlw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    IUserService userService;

    public User getByUsernameUser(String username) {
        LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<User>();
        lambdaQuery.eq(User::getUsername, username);
        return userDao.selectOne(lambdaQuery);
    }

    public List<User> getAll() {
        return userDao.selectList(null);
    }

    public boolean saveUser(User user) {
        return userService.save(user);
    }

    public boolean deleteUser(Integer id) {
        return userService.removeById(id);
    }

    public boolean updateUser(String username, String passwd) {
        User user = userService.getByUsernameUser(username);
        user.setPasswd(passwd);
        return userService.updateById(user);
    }

    public User getUserById(Integer id) {
        return userService.getById(id);
    }


}
