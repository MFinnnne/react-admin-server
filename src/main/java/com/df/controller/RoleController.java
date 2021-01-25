package com.df.controller;

import com.df.config.StatusCode;
import com.df.pojo.RestResult;
import com.df.pojo.Role;
import com.df.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "创建角色")
    @PostMapping("/createRoleByName")
    ResponseEntity<String> createRoleByName(@RequestBody String name) {
        int flag = roleService.addRole(name);
        return ResponseEntity.ok().body(flag == 1 ? "success" : "fall");

    }

    @ApiOperation(value = "创建角色")
    @PostMapping("/createRole")
    ResponseEntity<String> createRole(@RequestBody Role role) {
        int flag = roleService.insertSelective(role);
        return ResponseEntity.ok().body(flag == 1 ? "success" : "fall");
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/updateRole/{id}")
    ResponseEntity<String> updateRol2e(@PathVariable("id") Integer id, @RequestBody Role role) {
        role.setId(id);
        int update = roleService.updateByPrimaryKey(role);
        return ResponseEntity.ok().body(update == 1 ? "success" : "fall");

    }

    @ApiOperation(value = "根据id找到角色")
    @GetMapping("/get/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable Integer id){
        Role role = roleService.selectByPrimaryKey(id);
        return ResponseEntity.ok().body(role);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.notFound().build();
    }
}