package com.qlw.service;

import com.qlw.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author QLW
 * @since 2022-06-02
 */
public interface IUserService extends IService<User> {
    public User getByUsernameUser(String username);
    public List<User> getAll();
}
