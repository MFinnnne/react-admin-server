package com.df.controller;

import com.df.config.StatusCode;
import com.df.pojo.Category;
import com.df.pojo.RestResult;
import com.df.service.CategoryService;
import com.df.utils.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：MFine
 * @description：TODO
 * @date ：2020/10/14 15:50
 */
@Api(tags = "商品品类管理")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping(path = "/list/{parentId}")
    RestResult<List<Category>> findCategoryById(@PathVariable String parentId) {
        List<Category> categories = categoryService.findAllByParentId(parentId);
        if (categories.size() > 0) {
            return new RestResult<>(true, StatusCode.SUCCESS, "", categories);
        }
        return new RestResult<>(true, StatusCode.FAILED, "", categories);
    }

    @PostMapping(path = "/add")
    RestResult<Category> addCategory(@RequestBody Category category) {
        int isSuccess = categoryService.insert(category);
        return new RestResult<>(isSuccess >= 0, isSuccess >= 0 ? StatusCode.SUCCESS : StatusCode.FAILED, isSuccess >= 0 ? "" : "该品类已存在", category);
    }

    @ApiOperation(value = "商品品类更新")
    @PutMapping(path = "/update")
    RestResult<Category> updateCategory(@RequestBody Category category) {
        int isSuccess = categoryService.updateNameById(category.getName(), category.getId());
        return new RestResult<>(isSuccess >= 0, isSuccess >= 0 ? StatusCode.SUCCESS : StatusCode.FAILED, isSuccess >= 0 ? "" : "该品类不存在", category);
    }

    @PostMapping(path = "/findPage")
    ResponseEntity<Object> findPage(@RequestBody PageRequest pageRequest) {
        return ResponseEntity.ok().body(categoryService.findPage(pageRequest).getContent());
    }


    @GetMapping(value = "/findCategoryById/{id}")
    ResponseEntity<Category> findCategoryById(@PathVariable Integer id) {
        Category category = categoryService.selectByPrimaryKey(id);
        return ResponseEntity.ok().body(category);
    }


}