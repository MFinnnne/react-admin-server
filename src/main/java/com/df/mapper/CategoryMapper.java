package com.df.mapper;

import com.df.pojo.Category;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/10/15 22:59
 **/

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * Find all by parent id list.
     *
     * @param parentId the parent id
     * @return the list
     */
    List<Category> findAllByParentId(@Param("parentId") String parentId);

    /**
     * 根据品类名查找id
     *
     * @param name 品类名
     * @return id
     */
    List<Integer> findIdByName(@Param("name") String name);
}