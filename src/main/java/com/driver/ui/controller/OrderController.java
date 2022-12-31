package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.Converter.FoodConverter;
import com.driver.Converter.OrderConverter;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")

public class OrderController {

	@Autowired
	OrderServiceImpl orderService;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto orderDto = orderService.getOrderById(id);
		OrderDetailsResponse orderDetailsResponse = OrderConverter.DtoToResponse(orderDto);
		return orderDetailsResponse;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto orderDto = OrderDto.builder().items(order.getItems()).cost(order.getCost()).
				userId(order.getUserId()).build();
		OrderDto orderDto1 = orderService.createOrder(orderDto);
		OrderDetailsResponse orderDetailsResponse = OrderConverter.DtoToResponse(orderDto1);
		return orderDetailsResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto = OrderDto.builder().items(order.getItems()).cost(order.getCost()).
				userId(order.getUserId()).build();
		OrderDto orderDto1 = orderService.updateOrderDetails(id,orderDto);
		OrderDetailsResponse orderDetailsResponse = OrderConverter.DtoToResponse(orderDto1);
		return orderDetailsResponse;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		OperationStatusModel operationStatusModel = OperationStatusModel.
				builder().operationName(String.valueOf(RequestOperationName.DELETE))
				.operationResult(RequestOperationStatus.SUCCESS.name()).build();
		orderService.deleteOrder(id);
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> orderDtos = orderService.getOrders();
		List<OrderDetailsResponse> allOrders = new ArrayList<>();

		for(OrderDto orders : orderDtos){
			allOrders.add(OrderConverter.DtoToResponse(orders));
		}
		return  allOrders;
	}
}
