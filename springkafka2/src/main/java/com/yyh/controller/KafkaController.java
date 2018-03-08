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

    @RequestMapping(value = "/{topic}/send",method = RequestMethod.GET)
    public void sendMeessageTotopic1(@PathVariable String topic,@RequestParam(value = "partition",defaultValue = "0") int partition) {
        logger.info("start send message to {}",topic);
        kafkaTemplate.send(topic,partition,"你","好");
    }

}
