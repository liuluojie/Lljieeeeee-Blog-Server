package com.lljieeeeee.blog.dto.admin;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Article对象", description="")
public class ArticleListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "状态：0：发布，1：草稿,2:回收站")
    private Integer articleStatus;

    @ApiModelProperty(value = "文章阅读量")
    private Long articleVisits;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "发布时间")
    private Date publicTime;

    @ApiModelProperty(value = "最近编辑时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除，1：已删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "是否置顶")
    private Integer isTop;

    @ApiModelProperty(value = "点赞量")
    private Long articleLike;

    @ApiModelProperty(value = "文章分类")
    List<String> categoryList;

    @ApiModelProperty(value = "文章标签")
    List<String> tagList;
}
