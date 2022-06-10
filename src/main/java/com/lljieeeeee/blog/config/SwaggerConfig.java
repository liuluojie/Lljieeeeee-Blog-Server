package com.lljieeeeee.blog.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2021/3/2 16:35
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 *
 *
 * @EnableSwagger2 开启swagger注解
 * @Configuration  将当前类标记为spring boot配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig(){
        List<Parameter> pars = new ArrayList<Parameter>();
        ParameterBuilder ticketPar = new ParameterBuilder();
        ticketPar.name("Authorization").description("Token")
                .modelRef(new ModelRef("string")).parameterType("header")
                //header中的ticket参数非必填，传空也可以
                .required(false).build();
        //根据每个方法名也知道当前方法在设置什么参数
        pars.add((Parameter) ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/blog/v1/api/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/blog/v1/api/error.*")))
                .build().globalOperationParameters(pars)
                // 选择那些路径和api会生成document
                .select()
                // 对所有api进行监控
                .apis(RequestHandlerSelectors.any())
                //不显示错误的接口地址
                //错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                // 对根下所有路径进行监控
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("网站-个人博客系统API文档")
                .description("本文档描述了个人博客系统API接口定义")
                .version("1.0")
                .contact(new Contact("Lljieeeeee", "https://lljieeeeee.top", "2015743127@qq.com"))
                .build();
    }
}
