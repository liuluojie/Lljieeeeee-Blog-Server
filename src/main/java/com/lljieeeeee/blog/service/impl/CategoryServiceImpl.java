package com.lljieeeeee.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljieeeeee.blog.entity.Category;
import com.lljieeeeee.blog.mapper.CategoryMapper;
import com.lljieeeeee.blog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lljieeeeee.blog.util.page.PageUtil;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public Map<String, Object> getCategoryPage(long current, long size) {
        Page<Category> page = new Page<>(current, size);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        baseMapper.selectPage(page, wrapper);
        Map<String, Object> map = PageUtil.getPageInfo(page);
        return map;
    }

    @Override
    public Map<String, String> getCategoryMap() {
        List<Category> categoryList = baseMapper.selectList(null);
        Map<String, String> map = new HashMap<>();
        for (Category category : categoryList) {
            map.put(category.getCategoryId(), category.getCategoryName());
        }
        return map;
    }
}
