package com.yyh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {

    private final static Logger logger = LoggerFactory.getLogger(KafkaController.class);


    @Autowired
    private  KafkaTemplate<String,String> kafkaTemplate;


    @RequestMapping(value = "/foo/send",method = RequestMethod.GET)
    public void sendMeessage() {
        logger.info("start sned message to bar topic");
        kafkaTemplate.send("foo","hello","world");
    }


}
