package com.lljieeeeee.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lljieeeeee.blog.entity.ArticleCategoryRelation;
import com.lljieeeeee.blog.entity.ArticleTagRelation;
import com.lljieeeeee.blog.entity.Tag;
import com.lljieeeeee.blog.mapper.ArticleTagRelationMapper;
import com.lljieeeeee.blog.service.ArticleTagRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lljieeeeee.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleTagRelationServiceImpl extends ServiceImpl<ArticleTagRelationMapper, ArticleTagRelation> implements ArticleTagRelationService {


    @Autowired
    private TagService tagService;

    @Override
    public boolean saveTags(String articleId, List<String> articleTagList) {
        if (articleTagList == null) {
            return true;
        }
        for (String tagId : articleTagList) {
            QueryWrapper<Tag> wrapper = new QueryWrapper<>();
            wrapper.eq("tag_id", tagId);
            Tag selectOne = tagService.getOne(wrapper);
            if (selectOne == null) {
                Tag tag = new Tag();
                tag.setTagName(tagId);
                tagService.save(tag);
                wrapper = new QueryWrapper<>();
                wrapper.eq("tag_name", tagId);
                selectOne = tagService.getOne(wrapper);
                tagId = selectOne.getTagId();
            }
            ArticleTagRelation relation = new ArticleTagRelation();
            relation.setArticleId(articleId);
            relation.setTagId(tagId);
            int insert = baseMapper.insert(relation);
            if (insert < 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void removeTagByArticleId(String articleId) {
        QueryWrapper<ArticleTagRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<String> getTagListByArticleId(String articleId) {
        QueryWrapper<ArticleTagRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        List<ArticleTagRelation> articleTagRelationList = baseMapper.selectList(wrapper);
        List<String> list = new ArrayList<>();
        for (ArticleTagRelation articleTagRelation : articleTagRelationList) {
            list.add(articleTagRelation.getTagId());
        }
        return list;
    }
}
