package com.df.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.df.pojo.Products;

/**
 * The interface Products mapper.
 *
 * @author MFine
 * @version 1.0
 * @date 2020 /11/18 23:44
 */
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

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Products> findAll();

    /**
     * Count by id greater than integer.
     *
     * @param minId the min id
     * @return the integer
     */
    Integer countByIdGreaterThan(@Param("minId")Integer minId);

    /**
     * Find all by desc list.
     *
     * @param desc the desc
     * @return the list
     */
    List<Products> findAllByDesc(@Param("desc")String desc);

    List<Products> findAllByName(@Param("name")String name);



}