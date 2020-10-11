package com.df.controller;


import com.df.config.StatusCode;
import com.df.pojo.RestResult;
import com.df.pojo.User;
import com.df.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：MFine
 * @description：TODO
 * @date ：2020/10/5 19:23
 */
@RestController()
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/api/login", method = RequestMethod.POST)
    public RestResult<User> login(@RequestBody User user) {
        User findUser = userService.findOneByName(user.getName());
        System.out.println(findUser.toString());

        if (findUser.getPassword().equals(user.getPassword())) {
            return new RestResult<>(true, StatusCode.SUCCESS, findUser);
        }
        return new RestResult<>(false,StatusCode.FAILED);
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}