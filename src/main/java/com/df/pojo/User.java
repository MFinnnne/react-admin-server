package com.df.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/21 22:17
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    private String password;

    private String name;

    private String phone;

    private String email;

    private String roleId;

    private Long createTime;

    private Integer v;
}