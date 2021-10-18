package com.df.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/9/2 0:31
 **/
@Component
public class KafkaConsumer {
    @KafkaListener(topics = KafkaConstant.TOPIC, groupId = "kafka-springboot-001")
    public void consumer(ConsumerRecord<String, String> record, Acknowledgment ack) throws InterruptedException {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "接收到kafka消息,partition:" + record.partition() + ",offset:" + record.offset() + "value1:" + record.value());
        TimeUnit.SECONDS.sleep(1);
        ack.acknowledge();
    }
}
