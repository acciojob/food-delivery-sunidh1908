package com.driver.io.repository;

import com.driver.io.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
	OrderEntity findByOrderId(String orderId);

	void updateOrder(long id, String orderID, float cost, String[] items, String userID, boolean status);
}
