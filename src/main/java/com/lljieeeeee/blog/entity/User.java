package com.lljieeeeee.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户表id")
    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private String userId;

    @ApiModelProperty(value = "发布时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最近编辑时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String avatar;

    private String description;

    private String email;

    private String password;

    private String username;

    @ApiModelProperty(value = "是否删除：0：未删除，1：已删除")
    @TableLogic
    private Boolean isDeleted;


}
