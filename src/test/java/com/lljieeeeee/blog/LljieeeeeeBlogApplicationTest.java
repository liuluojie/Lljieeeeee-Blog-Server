package com.lljieeeeee.blog;

import com.lljieeeeee.blog.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author liuluojie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LljieeeeeeBlogApplicationTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void test1() {
        System.out.println(articleService.getArticlePage(1, 5));
        System.out.println(articleService.getArticlePageView(1, 5));

    }
}
