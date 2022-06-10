package com.lljieeeeee.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2021/9/21 11:06
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Component
@ConfigurationProperties(prefix = "sa-token.ignored")
public class IgnoreUrlsConfig {
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
