package com.topjoy.tacocloud.data;

import com.topjoy.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}
