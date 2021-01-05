package com.df.controller;


import com.df.config.StatusCode;
import com.df.pojo.RestResult;
import com.df.pojo.User;
import com.df.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}