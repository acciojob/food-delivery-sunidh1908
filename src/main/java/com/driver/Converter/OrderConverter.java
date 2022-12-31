package com.driver.Converter;

import com.driver.io.entity.OrderEntity;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {

    public static OrderDto EntityToDto(OrderEntity orderEntity){
        return OrderDto.builder().id(orderEntity.getId()).orderId(orderEntity.getOrderId()).
                cost(orderEntity.getCost()).items(orderEntity.getItems()).userId(orderEntity.getUserId()).
                status(orderEntity.isStatus()).build();
    }

    public static OrderDetailsResponse DtoToResponse(OrderDto orderDto){
        return OrderDetailsResponse.builder().orderId(orderDto.getOrderId()).cost(orderDto.getCost()).
                items(orderDto.getItems()).userId(orderDto.getUserId()).status(orderDto.isStatus()).build();
    }
}
