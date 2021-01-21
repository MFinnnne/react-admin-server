package com.df.mapper;

import com.df.pojo.User;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/21 23:13
 **/

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

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

    /**
     * Find all user
     *
     * @return user list
     */
    List<User> findAll();
}