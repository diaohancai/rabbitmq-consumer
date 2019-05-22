package com.hancai.rabbitmqconsumer.consume;

import com.hancai.rabbitmqconsumer.RabbitmqConsumerApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * rabbitmq consumer 测试
 *
 * @author diaohancai
 */
public class RabbitMQConsumerTest extends RabbitmqConsumerApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
     * 对于消费端来说，消费端不能感知到exchange的存在，消费端直接监听想要的queue即可
     */

    /**
     * /diao queue1 consume message test
     */
    @Test
    public void diaoQueue1ConsumeTest() {

    }

}
