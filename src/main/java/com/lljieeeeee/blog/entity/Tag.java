package com.lljieeeeee.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.lang.reflect.Field;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="Tag对象", description="")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标签表主键id")
    @TableId(value = "tag_id", type = IdType.ID_WORKER_STR)
    private String tagId;

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @ApiModelProperty(value = "发布时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最近编辑时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "标签封面图")
    private String tagImage;


}
