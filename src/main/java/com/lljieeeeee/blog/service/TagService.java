package com.lljieeeeee.blog.service;

import com.lljieeeeee.blog.entity.Tag;
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
public interface TagService extends IService<Tag> {

    /**
     * 分页查询标签
     * @param current
     * @param size
     * @return
     */
    Map<String, Object> getTagPage(long current, long size);

    /**
     * 获取标签Map集合
     * @return
     */
    Map<String, String> getTagMap();
}
