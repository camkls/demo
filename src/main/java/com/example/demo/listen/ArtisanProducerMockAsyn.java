package com.example.demo.listen;

import com.example.demo.user.entity.MessageMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Random;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Component
public class ArtisanProducerMockAsyn {

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate ;

    public ListenableFuture<SendResult<Object, Object>> sendMsgASync() throws Exception {
        // 模拟发送的消息
        Integer id = new Random().nextInt(100);
        MessageMock messageMock = new MessageMock(id, "messageSendByAsync-" + id);
        // 异步发送消息
        ListenableFuture<SendResult<Object, Object>> result = kafkaTemplate.send(TOPIC, messageMock);
        return result;
    }
}
