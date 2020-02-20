package com.warape.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanmingyu
 */
@Configuration
public class RabbitConfig {
    public static final String ORDER_QUEUE_NAME = "orderQueue";
    public static final String ORDER_ROUTING_KEY = "order_routing_key";
    public static final String ORDER_EXCHANGE_NAME = "order_exchange";

    /**
     * 将普通队列绑定到交换机上	 * 声明一个持久化队列 第二个参数true为持久化，在下次重启后自动加载队列，不设置也是默认持久化	 * @return
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingExchange(@Qualifier("orderQueue") Queue queue, @Qualifier("orderExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(ORDER_ROUTING_KEY);
    }

}
