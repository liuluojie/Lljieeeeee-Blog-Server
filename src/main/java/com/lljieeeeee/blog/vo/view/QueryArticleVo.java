package com.lljieeeeee.blog.vo.view;

import com.lljieeeeee.blog.entity.Article;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2022/1/20 21:55
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Article对象", description="")
public class QueryArticleVo implements Serializable {

    private String categoryName;

    private String tagName;
}
