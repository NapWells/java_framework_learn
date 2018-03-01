package com.yyh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {
    private final static Logger logger = LoggerFactory.getLogger(KafkaController.class);


    @Autowired
    private  KafkaTemplate<String,String> kafkaTemplate;


    @RequestMapping(value = "/{topic}/send",method = RequestMethod.GET)
    public void sendMeessage(
            @RequestParam(value = "message",defaultValue = "hello world") String message,
            @PathVariable final String topic) {
        logger.info("start sned message to {}",topic);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic,message);
        listenableFuture.addCallback(
                result -> logger.info("send message to {} success",topic),
                ex -> logger.info("send message to {} failure,error message:{}",topic,ex.getMessage()));
    }

    @RequestMapping(value = "/default/send",method = RequestMethod.GET)
    public void sendMeessagedefault() {
        logger.info("start send message to default topic");
        kafkaTemplate.sendDefault("你好，世界");
    }


}
