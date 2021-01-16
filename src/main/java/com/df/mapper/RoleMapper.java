package com.df.mapper;
import com.df.pojo.Role;

import java.util.List;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/6 23:52
 **/

public interface RoleMapper {

    /**
     * Delete by primary key int.
     *
     * @param id 主键id
     * @return 1 成功 0失败
     * @description: 根据id删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Insert int.
     *
     * @param record 插入对象
     * @return int
     * @description: 插入
     */
    int insert(Role record);

    /**
     * Insert selective int.  利用mybatis trim标签可以放出传入的record为空或者缺少参数
     *
     * @param record the record
     * @return int
     */
    int insertSelective(Role record);

    /**
     * Select by primary key role.
     *
     * @param id the id
     * @return the role
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(Role record);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<Role> findAll();

    /**
     * Count integer.
     *
     * @return the integer
     */
    Integer count();



}