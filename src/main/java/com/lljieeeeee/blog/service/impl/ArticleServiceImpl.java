package com.lljieeeeee.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljieeeeee.blog.dto.admin.ArticleListDTO;
import com.lljieeeeee.blog.dto.view.ArchiveArticle;
import com.lljieeeeee.blog.dto.view.ArchiveDTO;
import com.lljieeeeee.blog.entity.*;
import com.lljieeeeee.blog.mapper.ArticleMapper;
import com.lljieeeeee.blog.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lljieeeeee.blog.util.page.PageUtil;
import com.lljieeeeee.blog.vo.admin.ArticleAdminVo;
import com.lljieeeeee.blog.vo.view.QueryArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

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
    public Map<String, Object> getArticlePageByCategoryView(long current, long size, QueryArticleVo queryArticleVo) {
        // 根据分类名查询分类ID
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", queryArticleVo.getCategoryName());
        Category category = categoryService.getOne(categoryQueryWrapper);

        // 分页查询文章分类关系列表
        Page<ArticleCategoryRelation> articleCategoryRelationPage = new Page<>(current, size);
        QueryWrapper<ArticleCategoryRelation> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.eq("category_id", category.getCategoryId());
        articleCategoryRelationService.page(articleCategoryRelationPage, wrapper);
        List<ArticleCategoryRelation> articleCategoryRelationList = articleCategoryRelationPage.getRecords();

        // 根据文章ID查询文章列表
        List<ArticleListDTO> articleListDTOList = new ArrayList<>();
        for (ArticleCategoryRelation articleCategoryRelation : articleCategoryRelationList) {
            Article article = baseMapper.selectById(articleCategoryRelation.getArticleId());
            if (!StringUtils.isEmpty(article)) {
                ArticleListDTO articleListDTO = new ArticleListDTO();
                BeanUtils.copyProperties(article, articleListDTO);
                articleListDTOList.add(articleListDTO);
            }
        }
        Map<String, Object> map = PageUtil.getPageInfo(articleCategoryRelationPage);
        map.put("list", articleListDTOList);
        return map;
    }

    @Override
    public Map<String, Object> getArticlePageByTagView(long current, long size, QueryArticleVo queryArticleVo) {
        // 根据标签名查询标签ID
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.eq("tag_name", queryArticleVo.getTagName());
        Tag tag = tagService.getOne(tagQueryWrapper);

        // 分页查询文章标签关系列表
        Page<ArticleTagRelation> articleTagRelationPage = new Page<>(current, size);
        QueryWrapper<ArticleTagRelation> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.eq("tag_id", tag.getTagId());
        articleTagRelationService.page(articleTagRelationPage, wrapper);
        List<ArticleTagRelation> articleCategoryRelationList = articleTagRelationPage.getRecords();

        // 根据文章ID查询文章列表
        List<ArticleListDTO> articleListDTOList = new ArrayList<>();
        for (ArticleTagRelation articleTagRelation : articleCategoryRelationList) {
            Article article = baseMapper.selectById(articleTagRelation.getArticleId());
            if (!StringUtils.isEmpty(article)) {
                ArticleListDTO articleListDTO = new ArticleListDTO();
                BeanUtils.copyProperties(article, articleListDTO);
                articleListDTOList.add(articleListDTO);
            }
        }
        Map<String, Object> map = PageUtil.getPageInfo(articleTagRelationPage);
        map.put("list", articleListDTOList);
        return map;
    }

    @Override
    public List<ArchiveDTO> getArchiveList() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.eq("article_status", 0);
        List<Article> articleList = baseMapper.selectList(wrapper);
        List<ArchiveDTO> archiveDTOList = new ArrayList<>();
        int size = articleList.size();
        for (int i = 0, j = 0; i < size; ) {
            Article article = articleList.get(i);
            Integer year = DateUtil.year(article.getCreateTime());
            Integer month = DateUtil.month(article.getCreateTime());
            ArchiveDTO archiveDTO = new ArchiveDTO();
            archiveDTO.setYear(year);
            archiveDTO.setMonth(month + 1);
            List<ArchiveArticle> archiveArticleList = new ArrayList<>();
            for (j = i; j < size; j++) {
                Date createTime = articleList.get(j).getCreateTime();
                if(DateUtil.year(createTime) != year || DateUtil.month(createTime) != month) {
                    break;
                }
                ArchiveArticle archiveArticle = new ArchiveArticle();
                archiveArticle.setArticleId(articleList.get(j).getArticleId());
                archiveArticle.setArticleTitle(articleList.get(j).getArticleTitle());
                archiveArticle.setDate(DateUtil.dayOfMonth(createTime) + "");
                archiveArticleList.add(archiveArticle);
            }
            i = j;
            archiveDTO.setArchiveDTOList(archiveArticleList);
            archiveDTOList.add(archiveDTO);
        }
        return archiveDTOList;
    }
}
