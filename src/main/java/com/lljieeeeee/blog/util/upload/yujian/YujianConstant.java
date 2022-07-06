package com.lljieeeeee.blog.util.upload.yujian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2021/12/10 17:31
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
public class YujianConstant {

    public static final String TOKEN = "69357421efd12afd5740ed9d49418ded";

    public static final String UPLOAD_FAIL = "上传失败";
    private static final List<String> apiType = new ArrayList<>();
    private static void init() {

        apiType.addAll(Arrays.asList(
                "bilibili",
                "sougou",
                "toutiao",
                "huluxia",
                "sina",
                "catbox",
                "niupic",
                "baidu",
                "bcebos",
                "bjbcebos",
                "ouliu",
                "postimages",
                "chaoxing",
                "imgbox",
                "imageshack",
                "imgur",
                "vxichina",
                "renren",
                "taihe",
                "bkimg",
                "muke",
                "bitauto",
                "vimcn",
                "uploadcc",
                "ali",
                "smms",
                "gtimg"
        ));
    }

    public static List<String> getAPIType() {
        if (apiType.size() == 0) {
            init();
        }
        return apiType;
    }
}
