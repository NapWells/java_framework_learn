package com.yyh.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

public class MyMessageListener implements MessageListener<String, String> {
    public final static Logger logger = LoggerFactory.getLogger(MyMessageListener.class);

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        String topic = data.topic();
        logger.info("-------------recieve message from {} topic-------------", topic);
        logger.info("partition:{}", String.valueOf(data.partition()));
        logger.info("offset:{}", String.valueOf(data.offset()));
        logger.info("get message from {} topic : {}", topic, data.value());
    }

}
