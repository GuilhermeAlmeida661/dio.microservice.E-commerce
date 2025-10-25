package br.com.dio.storefront.service;

import br.com.dio.storefront.dto.StockStatusMessage;

// Interface to consume product availability change messages
public interface IProductChangeAvailabilityConsumer {

    void recieve(final StockStatusMessage message);

}
