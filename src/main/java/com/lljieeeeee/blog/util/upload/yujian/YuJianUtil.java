package com.lljieeeeee.blog.util.upload.yujian;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2021/12/10 17:29
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Slf4j
public class YuJianUtil {
    public static String upload(File file) throws Exception {
        String url = "https://www.hualigs.cn/api/upload?token=" + YujianConstant.TOKEN;
        List<String> apiType = YujianConstant.getAPIType();
        long start = System.currentTimeMillis();
        String imageUrl = "";
        for (String type : apiType) {
            log.info("url: {}", url + "&apiType=" + type);
            HttpRequest request = HttpRequest.post(url + "&apiType=" + type)
                    .form("image",file);
            HttpResponse response = request.execute();
            String body = response.body();
            log.info("body: {}", body);
            // 如果请求失败，则发起新的请求
            if (response.getStatus() != 200) {
                continue;
            }
            JSONObject jsonObject = JSON.parseObject(body);
            Integer code = jsonObject.getInteger("code");
            // 如果响应状态码不为 200，则发起新的请求
            if (code != 200) {
                continue;
            }
            JSONObject jsonUrl = jsonObject.getJSONObject("data").getJSONObject("url");
            imageUrl = jsonUrl.getString(type);
            if (!StringUtils.isEmpty(imageUrl) && !YujianConstant.UPLOAD_FAIL.equals(imageUrl)) {
                break;
            }
        }
        return imageUrl;
    }
}
