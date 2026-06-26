package cz.cvut.nss.orderservice.client;

import cz.cvut.nss.orderservice.dto.MenuItemDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogServiceClient {

    private final RestClient restClient;

    public CatalogServiceClient(@Value("${catalog.service.url:http://localhost:8081}") String catalogServiceUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(catalogServiceUrl)
                .build();
    }

    public MenuItemDto getMenuItem(String menuItemId) {
        return restClient.get()
                .uri("/menu-items/{id}", menuItemId)
                .retrieve()
                .body(MenuItemDto.class);
    }
}