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

    @ApiModelProperty(hidden = true)
    private Integer id;

    private String images;

    private Integer status;

    private String idStr;

    private String name;

    private String desc;

    private String price;

    private String pCategoryId;

    private String categoryId;

    private String detail;

    private Integer v;
}