package com.df.controller;

import com.df.config.StatusCode;
import com.df.pojo.Category;
import com.df.pojo.RestResult;
import com.df.service.CategoryService;
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
            return new RestResult<>(true, StatusCode.SUCCESS, categories);
        }
        return new RestResult<>(true, StatusCode.FAILED, categories);
    }

    @PostMapping(path = "/add")
    RestResult<Category> addCategory(@RequestBody Category category){
        int isSuccess = categoryService.insert(category);
        return new RestResult<>(isSuccess == 1, isSuccess == 1 ? StatusCode.SUCCESS : StatusCode.FAILED, category);
    }
}