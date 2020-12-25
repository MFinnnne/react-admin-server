package com.df.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MFine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id;

    private String parentId;

    private String name;

    private String categoryName;

    private String parentName;
}