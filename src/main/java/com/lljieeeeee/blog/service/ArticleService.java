package com.lljieeeeee.blog.service;

import com.lljieeeeee.blog.dto.view.ArchiveDTO;
import com.lljieeeeee.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lljieeeeee.blog.vo.admin.ArticleAdminVo;
import com.lljieeeeee.blog.vo.view.QueryArticleVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页查询文章列表
     * @param current
     * @param size
     * @return
     */
    Map<String, Object> getArticlePage(long current, long size);

    /**
     * 前台分页查询文章列表
     * @param current
     * @param size
     * @return
     */
    Map<String, Object> getArticlePageView(long current, long size);

    /**
     * 根据ID修改文章状态
     * @param articleId
     * @param status
     * @return
     */
    boolean updateArticleStatus(String articleId, Integer status);

    /**
     * 保存文章并返回文章ID
     * @param article
     * @return
     */
    String saveArticle(Article article);

    /**
     * 根据ID查询文章信息
     * @param articleId
     * @return
     */
    ArticleAdminVo getArticleInfo(String articleId);

    /**
     * 分页查询回收站文章列表
     * @param current
     * @param size
     * @return
     */
    Map<String, Object> getRecycleArticlePage(long current, long size);

    /**
     * 根据分类分页查询文章列表
     * @param current
     * @param size
     * @param queryArticleVo
     * @return
     */
    Map<String, Object> getArticlePageByCategoryView(long current, long size, QueryArticleVo queryArticleVo);

    /**
     * 根据标签分页查询文章列表
     * @param current
     * @param size
     * @param queryArticleVo
     * @return
     */
    Map<String, Object> getArticlePageByTagView(long current, long size, QueryArticleVo queryArticleVo);

    /**
     * 获取文章归档
     * @return
     */
    List<ArchiveDTO> getArchiveList();
}
