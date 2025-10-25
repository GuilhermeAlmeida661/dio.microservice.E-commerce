package br.com.dio.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class StorefrontClientConfig {

    // Cria um bean RestClient configurado com o basePath do Warehouse que está no application.yml
    @Bean
    RestClient storefrontClient(@Value("${storefront.base-path}") final String basePath){
            return RestClient.create(basePath);
    }
}
