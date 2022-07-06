package com.lljieeeeee.blog.controller.view;


import com.lljieeeeee.blog.entity.Category;
import com.lljieeeeee.blog.service.CategoryService;
import com.lljieeeeee.blog.util.result.R;
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
@RequestMapping("/view/category")
public class CategoryViewController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取分类map集合")
    @GetMapping("getCategoryMap")
    public R getCategoryMap() {
        Map<String, String> map = categoryService.getCategoryMap();
        return R.success().data("categoryMap", map);
    }

    @ApiOperation("获取所有分类")
    @GetMapping("getAllCategory")
    public R getAllCategory() {
        List<Category> list = categoryService.list(null);
        return R.success().data("list", list);
    }
}

