package com.df.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * *  角色表
 *
 * @author MFine
 * @date 2021/1/6 23:52
 * @version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色建立时间
     */
    private LocalDateTime createTime;

    /**
     * 有权限的页面
     */
    private String menus;

    /**
     * 版本
     */
    private Integer v;

    /**
     * 授权时间
     */
    private LocalDateTime authTime;

    /**
     * 授权人
     */
    private String authName;
}