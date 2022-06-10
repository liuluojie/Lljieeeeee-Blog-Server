package com.lljieeeeee.blog.controller.view;


import com.lljieeeeee.blog.entity.Tag;
import com.lljieeeeee.blog.service.CategoryService;
import com.lljieeeeee.blog.service.TagService;
import com.lljieeeeee.blog.utils.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
@RestController
@RequestMapping("/view/tag")
public class TagViewController {

    @Autowired
    private TagService tagService;

    @ApiOperation("获取标签map集合")
    @GetMapping("getTagMap")
    public R getTagMap() {
        Map<String, String> map = tagService.getTagMap();
        return R.success().data("tagMap", map);
    }

    @ApiOperation("获取所有标签")
    @GetMapping("getAllTag")
    public R getAllTag() {
        List<Tag> list = tagService.list(null);
        return R.success().data("list", list);
    }

}

