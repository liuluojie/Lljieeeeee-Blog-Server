package com.lljieeeeee.blog.controller.admin;


import com.lljieeeeee.blog.service.ArticleCategoryRelationService;
import com.lljieeeeee.blog.utils.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
@RestController
@RequestMapping("/admin/articleCategoryRelation")
public class ArticleCategoryRelationAdminController {

    @Autowired
    private ArticleCategoryRelationService articleCategoryRelationService;

    @PostMapping("addArticleCategories")
    public R addArticleCategories(@RequestBody List<String> relationList) {
        System.out.println(relationList);
        return R.success();
    }

    @DeleteMapping("deleteArticleCategories")
    public R deleteArticleCategories(@RequestBody List<String> relationList) {
        System.out.println(relationList);
        return R.success();
    }
}

