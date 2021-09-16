package com.rubicon.water.service;

import com.rubicon.water.dto.OrderDto;

import javax.transaction.Transactional;
import java.util.UUID;

public interface OrderService {

    @Transactional
    OrderDto newOrder(OrderDto orderDto);

    @Transactional
    OrderDto cancelWater(UUID orderId);
}
