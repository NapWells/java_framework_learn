package com.yyh.listener;

import org.springframework.kafka.annotation.KafkaListener;


public class MyListener {
    @KafkaListener(id = "foo", topics = "foo")
    public void listen1(String foo) {
        System.out.println(foo);
    }
}