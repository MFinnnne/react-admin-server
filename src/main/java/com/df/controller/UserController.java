package com.df.controller;


import com.df.config.StatusCode;
import com.df.pojo.RestResult;
import com.df.pojo.User;
import com.df.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：MFine
 * @description：TODO
 * @date ：2020/10/5 19:23
 */
@Api(value = "desc of class")
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "desc of method", notes = "")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public RestResult<User> login(@RequestBody User user) {
        User findUser = userService.findOneByName(user.getName());
        if (findUser.getPassword().equals(user.getPassword())) {
            return new RestResult<>(true, StatusCode.SUCCESS, "", new User(findUser.getId(), null, findUser.getName()));
        }
        return new RestResult<>(false, StatusCode.FAILED);
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}