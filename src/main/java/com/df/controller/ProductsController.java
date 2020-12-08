package com.df.controller;

import com.df.pojo.Products;
import com.df.service.ProductsService;
import com.df.utils.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.cucumber.java.sl.In;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/21 22:48
 **/
@Api(tags = "商品相关操作")
@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @ApiOperation(value = "按照描述搜索", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "desc", value = "商品描述", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "PageNum", value = "第几个开始", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "查询多少个", required = true, dataTypeClass = Integer.class)
    })
    @GetMapping("/searchByDesc/{desc}/{pageNum}/{pageSize}")
    public ResponseEntity<PageInfo<Products>> searchByDesc(@PathVariable String desc, @PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Products> pageInfo = PageInfo.of(productsService.findAllByDescLike(desc));
        return ResponseEntity.ok().body(pageInfo);
    }


    @ApiOperation(value = "查询商品", notes = "分页查询")
    @PostMapping("/list")
    public ResponseEntity<PageInfo<Products>> findAll(@RequestBody PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        PageInfo<Products> pageInfo = PageInfo.of(productsService.findAll());
        return ResponseEntity.ok().body(pageInfo);
    }

    @ApiOperation(value = "按照名字搜索", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品名称", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "PageNum", value = "第几个开始", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "查询多少个", required = true, dataTypeClass = Integer.class)
    })
    @GetMapping("/searchByName")
    public ResponseEntity<PageInfo<Products>> searchByName(@RequestParam(name="name") String name, @RequestParam(name = "pageNum") int pageNum, @RequestParam(name = "pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Products> pageInfo = PageInfo.of(productsService.findAllByNameLike(name));
        return ResponseEntity.ok().body(pageInfo);
    }

    @PutMapping(value = "/updateStatus/{id}")
    ResponseEntity<Integer> updateStatus(@RequestBody Map<String, Integer> map, @PathVariable Integer id){
        int flag = productsService.updateStatusById(map.get("status"), id);
        return ResponseEntity.ok().body(flag);
    }
}
