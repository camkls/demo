package com.example.demo;

import com.example.demo.listen.ArtisanProducerMock;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class Test {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private ArtisanProducerMock artisanProducerMock;

    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {
        SendResult sendResult = artisanProducerMock.sendMsgSync();

        logger.info("testSyncSend Result =  topic:[{}] , partition:[{}], offset:[{}]",
                sendResult.getRecordMetadata().topic(),
                sendResult.getRecordMetadata().partition(),
                sendResult.getRecordMetadata().offset());

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}