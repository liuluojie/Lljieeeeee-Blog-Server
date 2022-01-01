package com.lljieeeeee.blog.service;

import com.lljieeeeee.blog.entity.ArticleCategoryRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
public interface ArticleCategoryRelationService extends IService<ArticleCategoryRelation> {

    /**
     * 保存文章分类
     * @param articleId
     * @param articleCategoryList
     * @return
     */
    boolean saveCategories(String articleId, List<String> articleCategoryList);

    /**
     * 删除文章分类
     * @param articleId
     */
    void removeCategoryByArticleId(String articleId);

    /**
     * 根据文章ID查询分类
     * @param articleId
     * @return
     */
    List<String> getCategoriesByArticleId(String articleId);
}
