package com.df.controller;

import com.df.config.StatusCode;
import com.df.pojo.RestResult;
import com.df.pojo.Role;
import com.df.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/7 20:29
 **/
@Api(tags = "角色相关操作")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getRoles")
    ResponseEntity<RestResult<List<Role>>> getRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok().body(new RestResult<>(true, StatusCode.SUCCESS, "", roles == null ? new ArrayList<>() : roles));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.notFound().build();
    }
}
