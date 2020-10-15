package com.df.service;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.df.mapper.CategoryMapper;
import com.df.pojo.Category;

/**
 * @author MFine
 */
@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;


    public int deleteByPrimaryKey(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }


    public int insert(Category record) {
        List<Integer> id = findIdByName(record.getName());
        if (id.size() != 0) {
            return -1;
        } else {
            return categoryMapper.insert(record);
        }
    }


    public int insertSelective(Category record) {
        return categoryMapper.insertSelective(record);
    }


    public Category selectByPrimaryKey(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Category record) {
        return categoryMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Category record) {
        return categoryMapper.updateByPrimaryKey(record);
    }

    /**
     * @param parentId 父id
     * @return 品类数组
     */
    public List<Category> findAllByParentId(String parentId) {
        return categoryMapper.findAllByParentId(parentId);
    }

    /**
     * Find id by name list.
     *
     * @param name the name
     * @return the list
     */
    public List<Integer> findIdByName(String name) {
        return categoryMapper.findIdByName(name);
    }


}




