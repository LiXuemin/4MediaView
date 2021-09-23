package com.lxm.textconverter.config;

import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.jodconverter.core.DocumentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.lxm.textconverter.config.Constants.*;


@Configuration
public class ConsumerConfigure {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerConfigure.class);

    @Autowired
    private DocumentConverter converter;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP_TEXT_CONVERT);
        consumer.setNamesrvAddr(NAMESERV_ADDR);
        //consumer.setConsumeThreadMax(3);
        try {
            consumer.subscribe(TOPIC_TEXT_CONVERT, "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    try {
                        System.out.println(msgs);
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }catch (Exception e){
                        LOGGER.error("Consume msg and convert error: {}", e.getMessage(), e);
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return consumer;
    }
}
