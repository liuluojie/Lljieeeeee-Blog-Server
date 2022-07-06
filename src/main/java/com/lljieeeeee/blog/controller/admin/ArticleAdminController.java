package com.lljieeeeee.blog.controller.admin;


import com.lljieeeeee.blog.service.ArticleCategoryRelationService;
import com.lljieeeeee.blog.service.ArticleService;
import com.lljieeeeee.blog.service.ArticleTagRelationService;
import com.lljieeeeee.blog.util.result.R;
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
@RequestMapping("/admin/article")
public class ArticleAdminController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCategoryRelationService articleCategoryRelationService;

    @Autowired
    private ArticleTagRelationService articleTagRelationService;

    @ApiOperation("分页查询文章列表")
    @GetMapping("{current}/{size}")
    public R getArticlePage(@PathVariable("current")long current,
                            @PathVariable("size") long size) {
        Map<String, Object> map = articleService.getArticlePage(current, size);
        return R.success().data(map);
    }

    @ApiOperation("分页查询回收站文章列表")
    @GetMapping("getRecycleArticlePage/{current}/{size}")
    public R getRecycleArticlePage(@PathVariable("current")long current,
                            @PathVariable("size") long size) {
        Map<String, Object> map = articleService.getRecycleArticlePage(current, size);
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

    @ApiOperation("添加文章")
    @PostMapping
    public R addArticle(@RequestBody ArticleAdminVo articleAdminVo) {
        String articleId = articleService.saveArticle(articleAdminVo.getArticle());
        articleCategoryRelationService.saveCategories(articleId, articleAdminVo.getArticleCategoryList());
        articleTagRelationService.saveTags(articleId, articleAdminVo.getArticleTagList());
        if (StringUtils.isEmpty(articleId)) {
            return R.error().message("添加失败");
        }
        return R.success().message("添加成功").data("articleId", articleId);
    }

    @ApiOperation("根据ID修改文章")
    @PutMapping()
    public R updateArticle(@RequestBody ArticleAdminVo articleAdminVo) {
        if (articleAdminVo == null) {
            return R.error().message("文章信息不能为空");
        }
        boolean update = articleService.updateById(articleAdminVo.getArticle());

        //先删除之前的标签和分类
        articleCategoryRelationService.removeCategoryByArticleId(articleAdminVo.getArticle().getArticleId());
        articleTagRelationService.removeTagByArticleId(articleAdminVo.getArticle().getArticleId());
        // 在保存新的标签和分类
        articleCategoryRelationService.saveCategories(articleAdminVo.getArticle().getArticleId(), articleAdminVo.getArticleCategoryList());
        articleTagRelationService.saveTags(articleAdminVo.getArticle().getArticleId(), articleAdminVo.getArticleTagList());

        if (update) {
            return R.success().message("修改成功");
        }
        return R.error().message("修改失败");
    }

    @ApiOperation("根据ID修改文章状态")
    @PostMapping("{id}/{status}")
    public R updateArticleStatus(@PathVariable("id") String articleId,
                                 @PathVariable("status") Integer status) {
        boolean update = articleService.updateArticleStatus(articleId, status);
        if (update) {
            return R.success().message("修改文章状态成功");
        }
        return R.error().message("修改文章状态失败");
    }

    @ApiOperation("根据ID删除文章")
    @DeleteMapping("{id}")
    public R deleteArticle(@PathVariable("id") String articleId) {
        boolean remove = articleService.removeById(articleId);
        if (remove) {
            return R.success().message("删除文章成功");
        }
        return R.error().message("删除文章失败");
    }
}

