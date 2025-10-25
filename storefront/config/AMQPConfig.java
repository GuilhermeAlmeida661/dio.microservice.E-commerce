package br.com.dio.storefront.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    @Bean
    public Queue productChangeAvailabilityQueue() {
        return new Queue("product.change.availability.queue", true); // true = durable
    }

    //Serve para fazer a serialização dos objetos e deserealização java para o RabbitMQ
    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //Configura o RabbitTemplate para usar o conversor de mensagens JSON acima
    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, 
        final Jackson2JsonMessageConverter converter) {
            var rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(converter);
            return rabbitTemplate;
    }
}
