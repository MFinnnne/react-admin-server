package com.df.mapper;

import com.df.pojo.Role;

/**
*  @author MFine
*  @date 2021/1/6 23:52
*  @version 1.0
**/
    
public interface RoleMapper {

    /**
     * @description: 根据id删除
     * @param id 主键id
     * @return 1 成功 0失败
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @description: 插入
     * @param record 插入对象
     * @return
     */
    int insert(Role record);

    /**
     * @param record
     * @return
     */
    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}