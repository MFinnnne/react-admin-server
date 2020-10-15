package com;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.df.mapper.CategoryMapper;
import com.df.pojo.Category;
/**
*  @author MFine
*  @date 2020/10/15 22:58
*  @version 1.0
**/
    
@Service
public class CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Category record) {
        return categoryMapper.insert(record);
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

}
