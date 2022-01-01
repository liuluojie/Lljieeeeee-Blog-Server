package com.lljieeeeee.blog.service;

import com.lljieeeeee.blog.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
public interface CategoryService extends IService<Category> {

    /**
     * 分页查询文章分类
     * @param current
     * @param size
     * @return
     */
    Map<String, Object> getCategoryPage(long current, long size);

    /**
     * 获取分类Map集合
     * @return
     */
    Map<String, String> getCategoryMap();
}
