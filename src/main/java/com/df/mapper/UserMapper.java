package com.df.mapper;

import com.df.pojo.User;import org.apache.ibatis.annotations.Param;

/**
 * The interface User mapper.
 *
 * @author MFine
 * @version 1.0
 * @date 2021 /1/5 0:02
 */
public interface UserMapper {

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
    int insert(User record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(User record);

    /**
     * Select by primary key user.
     *
     * @param id the id
     * @return the user
     */
    User selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(User record);

    /**
     * Find one by name user.
     *
     * @param name the name
     * @return the user
     */
    User findOneByName(@Param("name") String name);

    /**
     * Find one id by name integer.
     *
     * @param name the name
     * @return the integer
     */
    Integer findOneIdByName(@Param("name") String name);
}