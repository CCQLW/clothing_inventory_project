package com.example.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.domain.User;
import com.example.service.IUserService;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author QLW
 * @since 2022-06-02
 */
@RestController
@RequestMapping("/user")
public class UserControllerResult {

    @Autowired
    private IUserService userService;

    @PostMapping
    public Result saveUser(@RequestBody User user) {
        return new Result(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        return new Result(userService.removeById(id));
    }

    @PutMapping("/{username}/{passwd}")
    public Result changePassword(@PathVariable String username, @PathVariable String passwd) {
        User user = userService.getByUsernameUser(username);
        user.setPasswd(passwd);
        return new Result(userService.updateById(user));
    }

    @GetMapping
    public Result getAll() {
        return new Result("success", userService.list());
    }

    @GetMapping("/id/{id}")
    public Result getUserById(@PathVariable Integer id) {
        return new Result("success", userService.getById(id));
    }

    @GetMapping("/username")
    public Result getUserByUsername(String username) {
        return new Result("success", userService.getByUsernameUser(username));
    }

    @PostMapping("/login")
    public Result login(String username, String passwd) {
        User user = userService.getByUsernameUser(username);
        if (user == null) {
            return new Result("用户不存在");
        } else {
            if (user.getPasswd().equals(passwd)) {
                return new Result("success", user);
            } else {
                return new Result("密码错误");
            }
        }
    }

    @PostMapping("/register")
    public Result register(String username, String passwd) {
        User user = userService.getByUsernameUser(username);
        if (user == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPasswd(passwd);
            return new Result(userService.save(newUser));
        } else {
            return new Result("用户已存在");
        }
    }

    @GetMapping("/update")
    public Result update(User user) {
        if (user.getUsername() != null) {
            User user1 = userService.getByUsernameUser(user.getUsername());
            if (user1 == null) {
                return new Result("用户不存在");
            } else {
                user.setId(user1.getId());
                return new Result(userService.updateById(user));
            }
        } else if (user.getId() != null) return new Result(userService.updateById(user));
        else return new Result("用户名与id不能同时为空");
    }

//    @Override  如果报错证明没有覆盖 springboot2-p40 8分23
//    @GetMapping
//    public List<User> getall(){
//        return userDao.selectList(null);
//    }
//
//    @GetMapping
//    public User getById(@PathVariable Integer id){
//        return userDao.selectById(id);
//    }

    }

