package com.qlw.controller;


import com.qlw.domain.User;
import com.qlw.service.IUserService;
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
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public boolean saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Integer id){
        return userService.removeById(id);
    }

    @PutMapping("/{username}/{passwd}")
    public boolean updateUser(@PathVariable String username,@PathVariable String passwd){
        User user =new User();
        user.setUsername(username);
        user.setPasswd(passwd);
        List<User> list = userService.list();
        for(int i =0;i<list.size();i++){
            if(username.equals(list.get(i).getUsername())){
                user.setId(i+1);
                break;
            }
        }
        return userService.updateById(user);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.list();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getById(id);
    }

//    public {
//
//        userService.removeById()
//    }


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

