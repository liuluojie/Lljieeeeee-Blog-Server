package com.lljieeeeee.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljieeeeee.blog.entity.Tag;
import com.lljieeeeee.blog.mapper.TagMapper;
import com.lljieeeeee.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lljieeeeee.blog.utils.page.PageUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public Map<String, Object> getTagPage(long current, long size) {
        Page<Tag> page = new Page<>(current, size);
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        baseMapper.selectPage(page, wrapper);
        Map<String, Object> map = PageUtil.getPageInfo(page);
        return map;
    }

    @Override
    public Map<String, String> getTagMap() {
        List<Tag> tagList = baseMapper.selectList(null);
        Map<String, String> map = new HashMap<>();
        for (Tag tag : tagList) {
            map.put(tag.getTagId(), tag.getTagName());
        }
        return map;
    }
}
