package com.example.blog.vo;

import lombok.Data;

@Data
public class CategoryVo {
    private String id;//id，图标路径，图标名称

    private String avatar;

    private String categoryName;

    private String description;
}
