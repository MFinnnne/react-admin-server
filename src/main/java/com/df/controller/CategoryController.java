package com.df.controller;

import com.df.config.StatusCode;
import com.df.pojo.Category;
import com.df.pojo.RestResult;
import com.df.service.CategoryService;
import com.df.utils.PageRequest;
import com.df.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：MFine
 * @description：TODO
 * @date ：2020/10/14 15:50
 */
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

    @PutMapping(path = "/update")
    RestResult<Category> updateCategory(@RequestBody Category category) {
        int isSuccess = categoryService.updateNameById(category.getName(), category.getId());
        return new RestResult<>(isSuccess >= 0, isSuccess >= 0 ? StatusCode.SUCCESS : StatusCode.FAILED, isSuccess >= 0 ? "" : "该品类不存在", category);
    }
    @PostMapping("findPage")
   public PageResult findPage(@RequestBody  PageRequest pageRequest) {
        return categoryService.findPage(pageRequest);
    }
}