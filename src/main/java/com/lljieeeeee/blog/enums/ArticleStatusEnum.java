package com.lljieeeeee.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuluojie
 */
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {

    /**
     * 发布
     */
    PUBLIC(0, "发布"),
    /**
     * 草稿
     */
    DRAFT(1, "草稿"),
    /**
     * 回收站
     */
    RECYCLE(2, "回收站");

    /**
     * 状态
     */
    private final Integer status;

    /**
     * 描述
     */
    private final String description;
}
