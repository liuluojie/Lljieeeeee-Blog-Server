package com.lljieeeeee.blog.dto.admin;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * @author Lljieeeeee
 * @date 2021/11/23 22:26
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class UserAdminDTO {

    private String userId;

    private String avatar;

    private String description;

    private String email;

    private String username;
}
