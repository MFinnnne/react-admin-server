package com.df.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.df.mapper.ProductsMapper;
import com.df.pojo.Products;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/18 23:37
 **/

@Service
public class ProductsService {

    @Resource
    private ProductsMapper productsMapper;


    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
    public int deleteByPrimaryKey(Integer id) {
        return productsMapper.deleteByPrimaryKey(id);
    }


    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    public int insert(Products record) {
        return productsMapper.insert(record);
    }


    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    public int insertSelective(Products record) {
        return productsMapper.insertSelective(record);
    }


    /**
     * Select by primary key products.
     *
     * @param id the id
     * @return the products
     */
    public Products selectByPrimaryKey(Integer id) {
        return productsMapper.selectByPrimaryKey(id);
    }


    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    public int updateByPrimaryKeySelective(Products record) {
        return productsMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    public int updateByPrimaryKey(Products record) {
        return productsMapper.updateByPrimaryKey(record);
    }

}


