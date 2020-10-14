package com.df.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MFine
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    private String password;

    private String name;
}