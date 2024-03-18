package com.techEnthusiast.orderservice.mapper;

import com.techEnthusiast.orderservice.dto.OrderLineItemsDto;
import com.techEnthusiast.orderservice.model.OrderLineItems;
import lombok.Data;
import lombok.RequiredArgsConstructor;

public class OrderMapper {

    public static OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
