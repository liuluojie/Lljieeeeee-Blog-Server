package com.lljieeeeee.blog.utils.upload.kieng;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.File;

/**
 * @author Lljieeeeee
 * @date 2021/6/1 21:21
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
public class KiengUtil {


    public static String upload(File file, String type) throws Exception {
        HttpRequest request = HttpRequest.post("https://image.kieng.cn/upload.html?type=" + type)
                .form("image",file);
        HttpResponse response = request.execute();
        String body = response.body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String url = jsonObject.getJSONObject("data").getStr("url");
        return url;
    }
}
