package com.chyld.messaging;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitconfig {

    @Bean
    public TopicExchange topic(){
        return new TopicExchange("fit.exchange");
    }

    @Bean
    public Queue runQueue() {
        return new Queue("fit.queue.run");
    }

    @Bean
    public Queue positionQueue() {
        return new Queue("fit.queue.position");
    }

    @Bean
    public Binding binding1(TopicExchange topic, Queue runQueue) {
        return BindingBuilder.bind(runQueue).to(topic).with("fit.topic.run.*");
    }

    @Bean
    public Binding binding2(TopicExchange topic, Queue positionQueue) {
        return BindingBuilder.bind(positionQueue).to(topic).with("fit.topic.position");
    }
}
