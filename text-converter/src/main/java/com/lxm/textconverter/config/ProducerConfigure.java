package com.lxm.textconverter.config;

import java.io.UnsupportedEncodingException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProducerConfigure {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProducerConfigure.class);

    @Bean
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer(Constants.PRODUCER_GROUP_TEXT_CONVERT);
        try {
            producer.setNamesrvAddr(Constants.NAMESERV_ADDR);
            producer.start();
            producer.setRetryTimesWhenSendAsyncFailed(3);
        } catch (MQClientException e) {
            LOGGER.error("Create DefaultMQProducer instance failed : {}", e.getMessage(), e);
        }
        return producer;
    }
}
