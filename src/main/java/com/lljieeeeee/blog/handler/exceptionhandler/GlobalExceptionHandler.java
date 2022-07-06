package com.lljieeeeee.blog.handler.exceptionhandler;

import cn.dev33.satoken.exception.NotLoginException;
import com.lljieeeeee.blog.exception.LljieeeeeeException;
import com.lljieeeeee.blog.util.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Lljieeeeee
 * @date 2021/3/2 17:41
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 指定出现什么异常执行这个方法
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e){
        log.error("{}", e.getMessage());
        return R.error().message("执行了全局异常处理...");
    }

    /**
     * 处理未登录的异常
     */
    @ResponseBody
    @ExceptionHandler(value = NotLoginException.class)
    public R handleNotLoginException(NotLoginException e) {
        log.error("{}", e.getMessage());
        return R.noToken().message("你没有权限访问该接口，因为：" + e.getMessage());
    }


    /**
     * 统一处理手动抛出的异常
     */
    @ResponseBody
    @ExceptionHandler(value = LljieeeeeeException.class)
    public R handleLljieeeeeeException(LljieeeeeeException e) {
        log.error("{}", e.getMessage());
        return R.error().message(e.getMessage());
    }
}
