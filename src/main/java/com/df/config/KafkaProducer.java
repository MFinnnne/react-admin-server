package com.df.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/9/2 0:28
 **/
@Component
public class KafkaProducer implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() ->
                        kafkaTemplate.send(KafkaConstant.TOPIC, String.valueOf(System.currentTimeMillis()))
                                .addCallback(result -> {
                                    if (null != result.getRecordMetadata()) {
                                        System.out.println("消费发送成功 offset:" + result.getRecordMetadata().offset());
                                        return;
                                    }
                                    System.out.println("消息发送成功");
                                }, throwable -> System.out.println("消费发送失败:" + throwable.getMessage())),
                0, 1, TimeUnit.SECONDS);
    }
}
