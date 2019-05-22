package com.hancai.rabbitmqconsumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author diaohancai
 */
@Configuration
public class RabbitMQConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * rabbitmq message json converter
     *
     * @return
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 消费端监听器factory
     *
     * @param connectionFactory
     * @param configurer
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory myRabbitFactory(CachingConnectionFactory connectionFactory,
                                                                SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                Jackson2JsonMessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(messageConverter); // 消息以json格式接收
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 手动 ack
        factory.setConcurrentConsumers(10); // 消费者并发数量
        factory.setMaxConcurrentConsumers(20); // 最大消费者并发数量
        factory.setPrefetchCount(5); // 一次从queue取多少条消息
        return factory;
    }

}
