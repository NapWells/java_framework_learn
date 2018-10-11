package com.yyh.server.message;

/**
 * @author lhy
 * @Date 2018/9/30 15:21
 * @JDK 1.7
 * @Description TODO
 */
public class Greeting {
    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
