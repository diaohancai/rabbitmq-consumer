package com.hancai.rabbitmqconsumer.consume;

import com.hancai.rabbitmqconsumer.model.Student;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * rabbitmq 消费端
 *
 * @author diaohancai
 */
@Component
public class RabbitMQConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * DeliveryTag 是queue发送给consume的消息中附带上的消息唯一标志，
     * 用于消费端指定DeliveryTag进行ack
     */

    @RabbitListener(containerFactory="myRabbitFactory", queues = "queue1")
    public void consumeQueue1(Message message, Channel channel, @Payload String payload) throws IOException {
        logger.info("Consumer deliveryTag: {}", message.getMessageProperties().getDeliveryTag());
        logger.info("Consumer receive: {}", payload);

        // 消费端手工ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(containerFactory="myRabbitFactory", queues = "queue2")
    public void consumeQueue2(Message message, Channel channel, @Payload Student student) throws IOException {
        logger.info("Consumer receive deliveryTag:{}, message:{}", message.getMessageProperties().getDeliveryTag(), student);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 消费端手工ack
        //channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 消费端拒绝ack
    }

}
