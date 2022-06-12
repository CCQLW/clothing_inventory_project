package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.User;
import com.example.service.IUserService;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
//    @PutMapping
//    public Result update(User user) {
//        return new Result(userService.updateById(user));
//    }

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
//    json 格式传
    public Result login(HttpServletRequest request, @RequestBody User userLogin) {
//    application/x-www-form-urlencoded 格式传参
//    public Result login(@RequestParam(value = "username") String username,@RequestParam(value = "passwd") String passwd) {
        String username = userLogin.getUsername();
        String passwd = userLogin.getPasswd();
        User user = userService.getByUsernameUser(username);
        if (user == null) {
            return new Result("用户不存在");
        } else {
            if (user.getPasswd().equals(passwd)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("passwd", passwd);
                return new Result("success", user);
            } else {
                return new Result("密码错误");
            }
        }
    }

    @Autowired
    HttpServletRequest request;   //首先可以通过注解的方式  获取一个 request

    @GetMapping("/logout")
    public Result logout(String username) {
        String usernameLogout = request.getSession().getAttribute("username").toString();
        if (usernameLogout.equals(username)) {
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("passwd");
            return new Result("success");
        } else {
            return new Result("fail");
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
        if("".equals(user.getUsername())){
            user.setUsername(null);
        }
        if("".equals(user.getPasswd())){
            user.setPasswd(null);
        }
        return new Result(userService.updateById(user));
//        if (user.getUsername() != null) {
//            User user1 = userService.getByUsernameUser(user.getUsername());
//            if (user1 == null) {
//                return new Result("用户不存在");
//            } else {
//                user.setId(user1.getId());
//                return new Result(userService.updateById(user));
//            }
//        } else if (user.getId() != null) return new Result(userService.updateById(user));
//        else return new Result("用户名与id不能同时为空");
    }

    @GetMapping("/page")
    public Result page(Integer page, Integer size) {
        Page<User> userPage=new Page<>(page,size);
        return new Result("success", userService.page(userPage));
    }


    @GetMapping("/fuzzy")
    public Result fuzzy(User user, Integer page, Integer size) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(user.getUsername()!=null,User::getUsername, user.getUsername());
        queryWrapper.eq(user.getAuthority()!=null,User::getAuthority, user.getAuthority());
        Page<User> pages = new Page<>(page, size);
        return new Result("success", userService.page(pages, queryWrapper));
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

