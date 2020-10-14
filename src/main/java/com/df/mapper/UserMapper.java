package com.df.mapper;

import com.df.pojo.User;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

@Mapper
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

    Integer findOneIdByName(@Param("name") String name);
}