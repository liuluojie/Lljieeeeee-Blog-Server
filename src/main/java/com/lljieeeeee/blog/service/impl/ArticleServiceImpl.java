package com.lljieeeeee.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljieeeeee.blog.dto.admin.ArticleListDTO;
import com.lljieeeeee.blog.entity.Article;
import com.lljieeeeee.blog.entity.ArticleCategoryRelation;
import com.lljieeeeee.blog.entity.ArticleTagRelation;
import com.lljieeeeee.blog.mapper.ArticleMapper;
import com.lljieeeeee.blog.service.ArticleCategoryRelationService;
import com.lljieeeeee.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lljieeeeee.blog.service.ArticleTagRelationService;
import com.lljieeeeee.blog.utils.page.PageUtil;
import com.lljieeeeee.blog.vo.admin.ArticleAdminVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleCategoryRelationService articleCategoryRelationService;

    @Autowired
    private ArticleTagRelationService articleTagRelationService;

    @Override
    public Map<String, Object> getArticlePage(long current, long size) {
        Page<Article> page = new Page<>(current, size);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.eq("article_status", 0).or().eq("article_status", 1);
        baseMapper.selectPage(page, wrapper);
        List<Article> list = page.getRecords();
        List<ArticleListDTO> articleListDTOList = new ArrayList<>();
        for (Article article : list) {
            ArticleListDTO articleListDTO = new ArticleListDTO();
            BeanUtils.copyProperties(article, articleListDTO);
            List<String> articleCategoryList = articleCategoryRelationService.getCategoriesByArticleId(article.getArticleId());
            articleListDTO.setCategoryList(articleCategoryList);
            List<String> articleTagList = articleTagRelationService.getTagListByArticleId(article.getArticleId());
            articleListDTO.setTagList(articleTagList);
            articleListDTOList.add(articleListDTO);
        }
        Map<String, Object> map = PageUtil.getPageInfo(page);
        map.put("list", articleListDTOList);
        return map;
    }

    @Override
    public Map<String, Object> getArticlePageView(long current, long size) {
        Page<Article> page = new Page<>(current, size);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.eq("article_status", 0);
        baseMapper.selectPage(page, wrapper);
        List<Article> list = page.getRecords();
        List<ArticleListDTO> articleListDTOList = new ArrayList<>();
        for (Article article : list) {
            ArticleListDTO articleListDTO = new ArticleListDTO();
            BeanUtils.copyProperties(article, articleListDTO);
            List<String> articleCategoryList = articleCategoryRelationService.getCategoriesByArticleId(article.getArticleId());
            articleListDTO.setCategoryList(articleCategoryList);
            List<String> articleTagList = articleTagRelationService.getTagListByArticleId(article.getArticleId());
            articleListDTO.setTagList(articleTagList);
            articleListDTOList.add(articleListDTO);
        }
        Map<String, Object> map = PageUtil.getPageInfo(page);
        map.put("list", articleListDTOList);
        return map;
    }

    @Override
    public boolean updateArticleStatus(String articleId, Integer status) {
        Article article = new Article();
        article.setArticleId(articleId);
        article.setArticleStatus(status);
        int update = baseMapper.updateById(article);
        return update > 0;
    }

    @Override
    public String saveArticle(Article article) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        int insert = baseMapper.insert(article);
        if (insert == 0) {
            return null;
        }
        wrapper.eq("article_title", article.getArticleTitle());
        wrapper.eq("article_content", article.getArticleContent());
        Article selectOne = baseMapper.selectOne(wrapper);
        return selectOne.getArticleId();
    }

    @Override
    public ArticleAdminVo getArticleInfo(String articleId) {
        Article article = baseMapper.selectById(articleId);
        QueryWrapper<ArticleCategoryRelation> articleCategoryRelationQueryWrapper = new QueryWrapper<>();
        articleCategoryRelationQueryWrapper.eq("article_id", articleId);
        List<ArticleCategoryRelation> articleCategoryRelationList = articleCategoryRelationService.list(articleCategoryRelationQueryWrapper);
        List<String> categoryList = new ArrayList<>();
        for (ArticleCategoryRelation articleCategoryRelation : articleCategoryRelationList) {
            categoryList.add(articleCategoryRelation.getCategoryId());
        }
        QueryWrapper<ArticleTagRelation> articleTagRelationQueryWrapper = new QueryWrapper<>();
        articleTagRelationQueryWrapper.eq("article_id", articleId);
        List<ArticleTagRelation> articleTagRelationList = articleTagRelationService.list(articleTagRelationQueryWrapper);
        List<String> tagList = new ArrayList<>();
        for (ArticleTagRelation articleTagRelation : articleTagRelationList) {
            tagList.add(articleTagRelation.getTagId());
        }
        ArticleAdminVo articleAdminVo = new ArticleAdminVo();
        articleAdminVo.setArticle(article);
        articleAdminVo.setArticleCategoryList(categoryList);
        articleAdminVo.setArticleTagList(tagList);
        return articleAdminVo;
    }

    @Override
    public Map<String, Object> getRecycleArticlePage(long current, long size) {
        Page<Article> page = new Page<>(current, size);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.eq("article_status", 2);
        baseMapper.selectPage(page, wrapper);
        List<Article> list = page.getRecords();
        List<ArticleListDTO> articleListDTOList = new ArrayList<>();
        for (Article article : list) {
            System.out.println(article);
            ArticleListDTO articleListDTO = new ArticleListDTO();
            BeanUtils.copyProperties(article, articleListDTO);
            List<String> articleCategoryList = articleCategoryRelationService.getCategoriesByArticleId(article.getArticleId());
            articleListDTO.setCategoryList(articleCategoryList);
            List<String> articleTagList = articleTagRelationService.getTagListByArticleId(article.getArticleId());
            articleListDTO.setTagList(articleTagList);
            articleListDTOList.add(articleListDTO);
        }
        Map<String, Object> map = PageUtil.getPageInfo(page);
        map.put("list", articleListDTOList);
        return map;
    }
}
