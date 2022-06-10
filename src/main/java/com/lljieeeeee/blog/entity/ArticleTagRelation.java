package com.lljieeeeee.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="ArticleTagRelation对象", description="")
public class ArticleTagRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关系表id")
    @TableId(value = "relation_id", type = IdType.ID_WORKER_STR)
    private String relationId;

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "标签id")
    private String tagId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
