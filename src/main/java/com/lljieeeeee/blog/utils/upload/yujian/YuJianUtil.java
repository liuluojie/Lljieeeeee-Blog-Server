package com.lljieeeeee.blog.utils.upload.yujian;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2021/12/10 17:29
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
public class YuJianUtil {
    public static String upload(File file) throws Exception {
        String url = "https://www.hualigs.cn/api/upload?token=" + YujianConstant.TOKEN;
        List<String> apiType = YujianConstant.getAPIType();
        String imageUrl = "";
        for (String type : apiType) {
            HttpRequest request = HttpRequest.post(url + "&apiType=" + type)
                    .form("image",file);
            HttpResponse response = request.execute();
            String body = response.body();
            JSONObject jsonObject = JSONUtil.parseObj(body);
            imageUrl = jsonObject.getJSONObject("data").getJSONObject("url").getStr(type);
            if (!StringUtils.isEmpty(imageUrl)) {
                break;
            }
        }
        return imageUrl;
    }
}
