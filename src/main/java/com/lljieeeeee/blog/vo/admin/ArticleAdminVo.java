package com.lljieeeeee.blog.vo.admin;

import com.baomidou.mybatisplus.annotation.*;
import com.lljieeeeee.blog.entity.Article;
import com.lljieeeeee.blog.entity.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2021/12/14 11:13
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Article对象", description="")
public class ArticleAdminVo implements Serializable {

    private Article article;

    private List<String> articleCategoryList;

    private List<String> articleTagList;
}
