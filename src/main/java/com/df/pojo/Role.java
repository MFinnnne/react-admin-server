package com.df.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * *  角色表
 *
 * @author MFine
 * @version 1.0
 * @date 2021/1/6 23:52
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "角色模型")
public class Role {
    /**
     * 主键
     */
    @ApiModelProperty(value = "角色id",example = "1",notes = "请求时可以不写")
    private Integer id;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名",example = "mfine")
    private String name;

    /**
     * 角色建立时间
     */
    @ApiModelProperty(value = "创建时间",example = "2021-01-12 16:16:14")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 有权限的页面
     */
    @ApiModelProperty(value = "橘色拥有全权限的页面",example = "/home,/products")
    private String menus;

    /**
     * 版本
     */
    @ApiModelProperty(value = "版本",example = "0",notes = "默认0,可以不写")
    private Integer v;

    /**
     * 授权时间
     */
    @ApiModelProperty(value = "授权时间",example = "2021-01-12 16:16:14")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime authTime;

    /**
     * 授权人
     */
    @ApiModelProperty(value = "授权人",example = "admin")
    private String authName;
}