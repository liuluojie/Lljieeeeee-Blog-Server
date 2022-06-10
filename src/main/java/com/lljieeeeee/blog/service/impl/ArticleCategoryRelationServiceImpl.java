package com.lljieeeeee.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lljieeeeee.blog.entity.ArticleCategoryRelation;
import com.lljieeeeee.blog.mapper.ArticleCategoryRelationMapper;
import com.lljieeeeee.blog.service.ArticleCategoryRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
@Service
public class ArticleCategoryRelationServiceImpl extends ServiceImpl<ArticleCategoryRelationMapper, ArticleCategoryRelation> implements ArticleCategoryRelationService {

    @Override
    public boolean saveCategories(String articleId, List<String> articleCategoryList) {
        if (articleCategoryList == null) {
            return true;
        }
        for (String categoryId : articleCategoryList) {
            ArticleCategoryRelation relation = new ArticleCategoryRelation();
            relation.setArticleId(articleId);
            relation.setCategoryId(categoryId);
            int insert = baseMapper.insert(relation);
            if (insert < 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void removeCategoryByArticleId(String articleId) {
        QueryWrapper<ArticleCategoryRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<String> getCategoriesByArticleId(String articleId) {
        QueryWrapper<ArticleCategoryRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        List<ArticleCategoryRelation> articleCategoryRelationList = baseMapper.selectList(wrapper);
        List<String> list = new ArrayList<>();
        for (ArticleCategoryRelation articleCategoryRelation : articleCategoryRelationList) {
            list.add(articleCategoryRelation.getCategoryId());
        }
        return list;
    }
}
