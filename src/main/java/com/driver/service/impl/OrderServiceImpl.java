package com.driver.service.impl;

import com.driver.Converter.OrderConverter;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity = OrderEntity.builder().id(order.getId()).orderId(order.getOrderId()).
                cost(order.getCost()).items(order.getItems()).userId(order.getUserId()).
                status(order.isStatus()).build();
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = OrderConverter.EntityToDto(orderEntity);
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = order;
        orderDto.setId(orderEntity.getId());
        orderRepository.updateOrder(orderEntity.getId(),orderEntity.getOrderId(),
                orderEntity.getCost(),orderEntity.getItems(),orderEntity.getUserId(),orderEntity.isStatus());
        return orderDto;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity> orderEntities = (List<OrderEntity>) orderRepository.findAll();
        List<OrderDto> allOrders = new ArrayList<>();

        for(OrderEntity order : orderEntities){
            allOrders.add(OrderConverter.EntityToDto(order));
        }
        return allOrders;
    }
}