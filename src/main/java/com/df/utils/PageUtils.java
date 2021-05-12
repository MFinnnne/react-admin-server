package com.df.utils;

import com.github.pagehelper.PageInfo;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/19 23:44
 **/

public class PageUtils {
    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest   分页请求信息
     * @param pageInfo 分页信息
     * @return 分页结果
     */
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
