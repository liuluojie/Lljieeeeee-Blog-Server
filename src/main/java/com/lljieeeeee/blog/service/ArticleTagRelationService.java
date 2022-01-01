package com.lljieeeeee.blog.service;

import com.lljieeeeee.blog.entity.ArticleTagRelation;
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
public interface ArticleTagRelationService extends IService<ArticleTagRelation> {

    /**
     * 保存文章标签
     * @param articleId
     * @param articleTagList
     * @return
     */
    boolean saveTags(String articleId, List<String> articleTagList);

    /**
     * 删除文章标签
     * @param articleId
     */
    void removeTagByArticleId(String articleId);

    /**
     * 根据文章ID查询标签
     * @param articleId
     * @return
     */
    List<String> getTagListByArticleId(String articleId);
}
