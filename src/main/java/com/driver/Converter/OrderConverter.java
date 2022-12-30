package com.driver.Converter;

import com.driver.io.entity.OrderEntity;
import com.driver.shared.dto.OrderDto;

public class OrderConverter {

    public static OrderDto EntityToDto(OrderEntity orderEntity){
        return OrderDto.builder().id(orderEntity.getId()).orderId(orderEntity.getOrderId()).
                cost(orderEntity.getCost()).items(orderEntity.getItems()).userId(orderEntity.getUserId()).
                status(orderEntity.isStatus()).build();
    }
}
