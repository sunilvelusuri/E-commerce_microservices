package com.techEnthusiast.orderservice.service;
import com.techEnthusiast.orderservice.dto.InventoryResponse;
import com.techEnthusiast.orderservice.dto.OrderRequest;
import com.techEnthusiast.orderservice.mapper.OrderMapper;
import com.techEnthusiast.orderservice.model.Order;
import com.techEnthusiast.orderservice.model.OrderLineItems;
import com.techEnthusiast.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest){
        Order order= new Order();
        order.setOrderNumber(UUID.randomUUID().toString());


        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                    .stream()
                    .map(OrderMapper::mapToDto)
                    .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode )
                .toList();

        InventoryResponse[] inventoryResponses = webClient.get()
                                .uri("http://localhost:8082/api/inventory",
                                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                                .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                .block();
        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);

        if(allProductsInStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product is Out of Stock");
        }

    }
}
