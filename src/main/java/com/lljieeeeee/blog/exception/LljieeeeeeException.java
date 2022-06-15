package com.lljieeeeee.blog.exception;


/**
 * @author liuluojie
 * @date 2022/6/13 10:45
 */
public class LljieeeeeeException extends RuntimeException{

    private static final long serialVersionUID = 1234529545290130132L;

    private String message;

    public LljieeeeeeException() {

    }

    public LljieeeeeeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
