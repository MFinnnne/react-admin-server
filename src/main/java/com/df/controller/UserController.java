package com.df.controller;


import com.df.config.StatusCode;
import com.df.pojo.RestResult;
import com.df.pojo.User;
import com.df.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：MFine
 * @description：TODO
 * @date ：2020/10/5 19:23
 */
@Api(tags = "用户相关操作")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户登录", notes = "status为0则登录成功，id参数可以为空")
    @RequestMapping(path = "/login", method = RequestMethod.POST, consumes = {"application/json"})
    public RestResult<User> login(@RequestBody User user) {
        User findUser = userService.findOneByName(user.getName());
        if (findUser.getPassword().equals(user.getPassword())) {
            return new RestResult<>(true, StatusCode.SUCCESS, "", findUser);
        }
        return new RestResult<>(false, StatusCode.FAILED);
    }


    @ApiOperation(value = "获取所有用户")
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleStorageFileNotFound(Exception exc) {
        return ResponseEntity.notFound().build();
    }

}