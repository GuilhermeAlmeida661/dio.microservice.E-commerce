package br.com.dio.storefront.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import br.com.dio.storefront.dto.StockStatusMessage;
import br.com.dio.storefront.service.IProductChangeAvailabilityConsumer;
import br.com.dio.storefront.service.IProductService;
import lombok.AllArgsConstructor;


// Implementation of the consumer for product availability change messages
@AllArgsConstructor
@Service
public class ProductChangeAvailabilityConsumerImpl implements IProductChangeAvailabilityConsumer{

    private final IProductService service;

    @RabbitListener(queues = "${spring.rabbitmq.queue.product-change-availability}")
    @Override
    public void recieve(final StockStatusMessage message) {
        service.changeActivated(message.id(), message.active());
    }

}
