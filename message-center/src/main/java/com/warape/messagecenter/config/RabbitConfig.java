package com.warape.messagecenter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.warape.commons.constants.RabbitConstants;


/**
 * @author wanmingyu
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * 将普通队列绑定到交换机上	 * 声明一个持久化队列 第二个参数true为持久化，在下次重启后自动加载队列，不设置也是默认持久化	 * @return
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(RabbitConstants.ORDER_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(RabbitConstants.ORDER_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingExchange(@Qualifier("orderQueue") Queue queue, @Qualifier("orderExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(RabbitConstants.ORDER_ROUTING_KEY);
    }

}
