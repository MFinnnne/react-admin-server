package com.df.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/18 23:44
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "商品模型")
public class Products {

    @ApiModelProperty(value = "商品id",example = "1")
    private Integer id;

    @ApiModelProperty(value = "图片名字",example = "1.jpg,2.png")
    private String images;

    @ApiModelProperty(value = "在售:1 停售:0",example = "1",required = true)
    private Integer status;

    @ApiModelProperty(value = "id",notes = "自己生成的可以无视",example = "1915ca46cf9e44d5bt4446fb20563351")
    private String idStr;

    @ApiModelProperty(value = "商品名字",example = "小米8",required = true)
    private String name;

    @ApiModelProperty(value = "详情描述",example = "这就是手机",required = true)
    private String desc;

    @ApiModelProperty(value = "价格",example = "2450",required = true)
    private String price;

    @ApiModelProperty(value = "所属分类的父类id",example = "0",required = true)
    private String pCategoryId;

    @ApiModelProperty(value ="所属分类id",example = "5",required = true)
    private String categoryId;

    @ApiModelProperty(value = "详细描述",example = "<p>还阔以</p>",required = true)
    private String detail;

    @ApiModelProperty(value = "或许是版本号吧",example = "0",notes = "目前默认是0")
    private Integer v;
}