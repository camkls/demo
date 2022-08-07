package com.example.demo;

import com.example.demo.listen.ArtisanProducerMockAsyn;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.junit.Test;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestAsyn {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArtisanProducerMockAsyn artisanProducerMockAsyn;

    @Test
    public void testAsynSend() throws Exception {
        artisanProducerMockAsyn.sendMsgASync().addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info(" 发送异常{}]]", throwable);

            }
            @Override
            public void onSuccess(SendResult<Object, Object> objectObjectSendResult) {
                logger.info("回调结果 Result =  topic:[{}] , partition:[{}], offset:[{}]",
                        objectObjectSendResult.getRecordMetadata().topic(),
                        objectObjectSendResult.getRecordMetadata().partition(),
                        objectObjectSendResult.getRecordMetadata().offset());
            }
        });

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
