package com.df.mapper;

import com.df.pojo.Products;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/18 23:44
 **/

public interface ProductsMapper {
    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(Products record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(Products record);

    /**
     * Select by primary key products.
     *
     * @param id the id
     * @return the products
     */
    Products selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(Products record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(Products record);


}