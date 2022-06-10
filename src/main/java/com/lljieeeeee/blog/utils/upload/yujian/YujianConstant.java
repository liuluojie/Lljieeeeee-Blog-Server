package com.lljieeeeee.blog.utils.upload.yujian;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2021/12/10 17:31
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
public class YujianConstant {

    public static final String TOKEN = "69357421efd12afd5740ed9d49418ded";

    private static final List<String> apiType = new ArrayList<>();
    private static void init() {
        //apiType.add("gtimg");
        apiType.add("ai58");
        apiType.add("sougou");
        apiType.add("bilibili");
        apiType.add("toutiao");
        apiType.add("huluxia");
        apiType.add("sina");
        apiType.add("catbox");
        apiType.add("niupic");
        apiType.add("baidu");
        apiType.add("bcebos");
        apiType.add("bjbcebos");
        apiType.add("ouliu");
        apiType.add("postimages");
        apiType.add("chaoxing");
        apiType.add("imgbox");
        apiType.add("imgur");
        apiType.add("vxichina");
        apiType.add("muke");
        apiType.add("vimcn");
        apiType.add("ali");
    }

    public static List<String> getAPIType() {
        if (apiType.size() == 0) {
            init();
        }
        return apiType;
    }
}
