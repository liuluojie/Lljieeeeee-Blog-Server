package com.lljieeeeee.blog.controller.view;


import com.lljieeeeee.blog.dto.view.ArchiveDTO;
import com.lljieeeeee.blog.service.ArticleService;
import com.lljieeeeee.blog.utils.redis.RedisUtil;
import com.lljieeeeee.blog.utils.result.R;
import com.lljieeeeee.blog.vo.admin.ArticleAdminVo;
import com.lljieeeeee.blog.vo.view.QueryArticleVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("分页查询文章列表")
    @GetMapping("{current}/{size}")
    public R getArticlePage(@PathVariable("current")long current,
                            @PathVariable("size") long size) {
        Map<String, Object> map = articleService.getArticlePageView(current, size);
        return R.success().data(map);
    }

    @ApiOperation("根据分类分页查询文章列表")
    @PostMapping("getArticlePageByCategory/{current}/{size}")
    public R getArticlePageByCategory(@PathVariable("current")long current,
                                      @PathVariable("size") long size,
                                      @RequestBody QueryArticleVo queryArticleVo) {
        Map<String, Object> map = articleService.getArticlePageByCategoryView(current, size, queryArticleVo);
        return R.success().data(map);
    }

    @ApiOperation("根据标签分页查询文章列表")
    @PostMapping("getArticlePageByTag/{current}/{size}")
    public R getArticlePageByTag(@PathVariable("current")long current,
                                      @PathVariable("size") long size,
                                      @RequestBody QueryArticleVo queryArticleVo) {
        Map<String, Object> map = articleService.getArticlePageByTagView(current, size, queryArticleVo);
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

    @ApiOperation("获取文章归档")
    @GetMapping("getArchiveList")
    public R getArchiveList() {
        if (redisUtil.hasKey("ArchiveList")) {
            return R.success().data("list", redisUtil.lGet("ArchiveList", 0L, -1L));
        }
        List<ArchiveDTO> list = articleService.getArchiveList();
        redisUtil.lSet("ArchiveList", list);
        return R.success().data("list", list);
    }
}

