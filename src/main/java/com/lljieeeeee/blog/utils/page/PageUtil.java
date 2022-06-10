package com.lljieeeeee.blog.utils.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lljieeeeee
 * @date 2021/3/2 16:47
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
public class PageUtil {

    /**
     * 封装查询到的分页数据
     * @param page
     * @param <T>
     * @return
     */
    public static <T> Map<String,Object> getPageInfo(Page<T> page) {

        List<T> records = page.getRecords();
        long current = page.getCurrent();
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>(8);
        map.put("list", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
}
