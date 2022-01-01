package com.lljieeeeee.blog.controller.view;


import com.lljieeeeee.blog.entity.User;
import com.lljieeeeee.blog.service.ArticleCategoryRelationService;
import com.lljieeeeee.blog.service.ArticleService;
import com.lljieeeeee.blog.service.ArticleTagRelationService;
import com.lljieeeeee.blog.service.UserService;
import com.lljieeeeee.blog.utils.md5.Md5;
import com.lljieeeeee.blog.utils.result.R;
import com.lljieeeeee.blog.vo.admin.ArticleAdminVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
@RestController
@RequestMapping("/view/article")
public class ArticleViewController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("分页查询文章列表")
    @GetMapping("{current}/{size}")
    public R getArticlePage(@PathVariable("current")long current,
                            @PathVariable("size") long size) {
        Map<String, Object> map = articleService.getArticlePageView(current, size);
        return R.success().data(map);
    }

    @ApiOperation("根据ID获取文章信息")
    @GetMapping("{id}")
    public R getArticleById(@PathVariable("id")String articleId) {
        ArticleAdminVo articleInfo = articleService.getArticleInfo(articleId);
        if (StringUtils.isEmpty(articleInfo)) {
            return R.error().message("没有查询到文章");
        }
        return R.success().message("查询到了文章").data("articleInfo", articleInfo);
    }
}

