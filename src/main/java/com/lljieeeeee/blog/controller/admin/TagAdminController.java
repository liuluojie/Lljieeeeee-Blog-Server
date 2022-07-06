package com.lljieeeeee.blog.controller.admin;


import com.lljieeeeee.blog.entity.Tag;
import com.lljieeeeee.blog.service.TagService;
import com.lljieeeeee.blog.util.result.R;
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
@RequestMapping("/admin/tag")
public class TagAdminController {

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

    @ApiOperation("分页查询标签")
    @GetMapping("{current}/{size}")
    public R getTagPage(@PathVariable("current") long current,
                        @PathVariable("size") long size) {
        Map<String, Object> map = tagService.getTagPage(current, size);
        return R.success().data(map);
    }

    @ApiOperation("根据ID查询标签")
    @GetMapping("{id}")
    public R getTagById(@PathVariable("id") String tagId) {
        Tag tag = tagService.getById(tagId);
        if (StringUtils.isEmpty(tag)) {
            return R.error().message("没有查到标签");
        }
        return R.success().message("查到了便签").data("tag", tag);
    }

    @ApiOperation("添加标签")
    @PostMapping
    public R addTag(@RequestBody Tag tag) {
        boolean save = tagService.save(tag);
        if (save) {
            return R.success().message("添加成功");
        }
        return R.error().message("添加失败");
    }

    @ApiOperation("根据ID修改标签")
    @PutMapping
    public R updateTag(@RequestBody Tag tag) {
        boolean update = tagService.updateById(tag);
        if (update) {
            return R.success().message("修改成功");
        }
        return R.error().message("修改失败");
    }

    @ApiOperation("根据ID删除标签")
    @DeleteMapping("{id}")
    public R deleteTag(@PathVariable("id") String tagId) {
        boolean remove = tagService.removeById(tagId);
        if (remove) {
            return R.success().message("删除成功");
        }
        return R.error().message("删除失败");
    }
}

