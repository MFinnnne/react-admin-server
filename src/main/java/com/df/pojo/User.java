package com.df.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/21 23:13
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户实体类")
public class User {
    @ApiModelProperty(value = "用户id", name = "id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "用户密码", name = "password", example = "12123")
    private String password;

    @ApiModelProperty(value = "用户名", name = "name", example = "zhangsan")
    private String name;

    @ApiModelProperty(value = "电话号码", name = "phone", example = "13584574374")
    private String phone;

    @ApiModelProperty(value = "邮箱", name = "email", example = "lxemyf@gmail.com")
    private String email;

    @ApiModelProperty(value = "角色id", name = "roleId", example = "1")
    private String roleId;

    @ApiModelProperty(value = "创建时间", name = "creatTime", example = "2020-01-01 00:00:00")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "不知道干啥好,可忽略", name = "v", example = "1")
    private Integer v;;
}