package com.lljieeeeee.blog.dto.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author liuluojie
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ArchiveDTO", description="")
public class ArticleDetailDTO {

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "文章封面图")
    private String articleCoverImage;

    @ApiModelProperty(value = "文章内容")
    private String articleContent;

    @ApiModelProperty(value = "文章HTML文本")
    private String articleHtml;

    @ApiModelProperty(value = "文章摘要")
    private String articleSummary;

    @ApiModelProperty(value = "文章阅读量")
    private Long articleVisits;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "发布时间")
    private Date publicTime;

    @ApiModelProperty(value = "最近编辑时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否置顶")
    private Integer isTop;

    @ApiModelProperty(value = "点赞量")
    private Long articleLike;

    @ApiModelProperty(value = "文章分类")
    List<String> categoryList;

    @ApiModelProperty(value = "文章标签")
    List<String> tagList;
}
