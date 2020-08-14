package com.topjoy.tacocloud.data;

import com.topjoy.tacocloud.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
//    Order save(Order order);
}
