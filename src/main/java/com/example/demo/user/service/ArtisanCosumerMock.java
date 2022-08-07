package com.example.demo.user.service;

import com.example.demo.user.entity.MessageMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
public class ArtisanCosumerMock {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String CONSUMER_GROUP_PREFIX = "MOCK-A" ;

    @KafkaListener(topics = TOPIC ,groupId = CONSUMER_GROUP_PREFIX + TOPIC)
    public void onMessage(MessageMock messageMock){
        logger.info("【接受到消息][线程:{} 消息内容：{}]", Thread.currentThread().getName(), messageMock);
    }
}
