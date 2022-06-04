package com.example.controller;


import com.example.domain.User;
import com.example.service.IUserService;
import com.qlw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
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
    public Result saveUser(@RequestBody User user){
        return new Result(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return new Result(userService.removeById(id));
    }

    @PutMapping("/{username}/{passwd}")
    public Result changePassword(@PathVariable String username,@PathVariable String passwd){
        User user  = userService.getByUsernameUser(username);
        user.setPasswd(passwd);
        return new Result(userService.updateById(user));
    }

    @GetMapping
    public Result getAll(){
        return new Result("success",userService.list());
    }

    @GetMapping("/id/{id}")
    public Result getUserById(@PathVariable Integer id){
        return new Result("success",userService.getById(id));
    }

    @GetMapping("/username/{username}")
    public Result getUserByUsername(@PathVariable String username){
        System.out.println(username);
        return new Result("success",userService.getByUsernameUser(username));
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

