package com.df.mapper;

import com.df.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Category mapper.
 * @author MFine
 */
@Mapper
public interface CategoryMapper {
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
    int insert(Category record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(Category record);

    /**
     * Select by primary key category.
     *
     * @param id the id
     * @return the category
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
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

    /**
     * Update by name int.
     *
     * @param updated the updated
     * @param name    the name
     * @return the int
     */
    int updateByName(@Param("updated") Category updated, @Param("name") String name);

    /**
     * Update name by name int.
     *
     * @param updatedName the updated name
     * @param name        the name
     * @return the int
     */
    int updateNameByName(@Param("updatedName") String updatedName, @Param("name") String name);

    /**
     * Update name by id int.
     *
     * @param updatedName the updated name
     * @param id          the id
     * @return the int
     */
    int updateNameById(@Param("updatedName") String updatedName, @Param("id") Integer id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Category> findAll();
}