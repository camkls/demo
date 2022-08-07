package com.example.demo.listen;

import com.example.demo.user.entity.MessageMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;


@Component
public class ArtisanProducerMock {

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate ;


    /**
     * 同步发送
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public SendResult sendMsgSync() throws ExecutionException, InterruptedException {
        // 模拟发送的消息
        Integer id = new Random().nextInt(100);
        MessageMock messageMock = new MessageMock(id,"artisanTestMessage-" + id);
        // 同步等待
        return  kafkaTemplate.send(TOPIC, messageMock).get();
    }
}
