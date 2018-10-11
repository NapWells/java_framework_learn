package com.yyh.server.message;

import java.io.Serializable;

/**
 * @author lhy
 * @Date 2018/9/30 15:18
 * @JDK 1.7
 * @Description TODO
 */
public class HelloMessage implements Serializable {

    private String name;

    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
