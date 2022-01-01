package com.lljieeeeee.blog.controller.admin;


import com.lljieeeeee.blog.entity.Category;
import com.lljieeeeee.blog.service.CategoryService;
import com.lljieeeeee.blog.utils.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/admin/category")
public class CategoryAdminController {

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

    @ApiOperation("分页查询文章分类")
    @GetMapping("{current}/{size}")
    public R getCategoryPage(@PathVariable("current") long current,
                             @PathVariable("size") long size) {
        Map<String, Object> map = categoryService.getCategoryPage(current, size);
        return R.success().data(map);
    }

    @ApiOperation("根据ID获取文章分类")
    @GetMapping("{id}")
    public R getCategoryById(@PathVariable("id") String categoryId) {
        Category category = categoryService.getById(categoryId);
        if (StringUtils.isEmpty(category)) {
            return R.error().message("没有查到分类信息");
        }
        return R.success().message("查到了分类").data("category", category);
    }

    @ApiOperation("添加分类")
    @PostMapping
    public R addCategory(@RequestBody Category category) {
        boolean save = categoryService.save(category);
        if (save) {
            return R.success().message("添加分类成功");
        }
        return R.error().message("添加分类失败");
    }

    @ApiOperation("根据ID修改分类")
    @PutMapping
    public R updateCategory(@RequestBody Category category) {
        boolean update = categoryService.updateById(category);
        if (update) {
            return R.success().message("修改分类成功");
        }
        return R.error().message("修改分类失败");
    }

    @ApiOperation("根据ID删除分类")
    @DeleteMapping("{id}")
    public R deleteCategory(@PathVariable("id") String categoryId) {
        boolean remove = categoryService.removeById(categoryId);
        if (remove) {
            return R.success().message("删除分类成功");
        }
        return R.error().message("删除分类失败");
    }
}

