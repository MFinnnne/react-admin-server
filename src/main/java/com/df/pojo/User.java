package com.df.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MFine
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户基本信息")
public class User {

    @ApiModelProperty(example = "1",notes = "可以不写这个属性")
    private Integer id;

    @ApiModelProperty(example = "123",required = true)
    private String password;

    @ApiModelProperty(example = "admin",required = true)
    private String name;

}