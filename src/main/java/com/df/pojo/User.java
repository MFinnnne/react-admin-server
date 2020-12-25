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

    @ApiModelProperty(example = "1",notes = "可以不写这个属性",hidden = true)
    private Integer id;

    private String password;

    private String name;

}