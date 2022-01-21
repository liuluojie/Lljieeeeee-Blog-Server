package com.lljieeeeee.blog.dto.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Lljieeeeee
 * @date 2022/1/21 15:58
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ArchiveArticle对象", description="")
public class ArchiveArticle implements Serializable {


    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "发布时间")
    private String date;
}