package com.rubicon.water.service.impl;

import com.rubicon.water.dto.OrderDto;
import com.rubicon.water.entity.Order;
import com.rubicon.water.entity.WaterOrder;
import com.rubicon.water.mapper.OrderMapper;
import com.rubicon.water.repository.OrderRepository;
import com.rubicon.water.repository.WaterRepository;
import com.rubicon.water.service.OrderService;
import com.rubicon.water.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WaterRepository waterRepository;

    @Override
    public OrderDto newOrder(OrderDto orderDto) {

        try{

            Order order = new Order();

            Optional<WaterOrder> waterOrder = waterRepository.findById(orderDto.getWaterOrder().toString());
            if(!waterOrder.isPresent())
                return null;
            order.setWaterOrder(waterOrder.get());

            Timestamp startTimeStamp= CommonUtils.stringToTimeStamp(orderDto.getStartTime());
            Timestamp endTimeStamp = CommonUtils.generateEndTime(startTimeStamp, orderDto.getDuration());

            List<Order> waterOrders = orderRepository.getAllOrders(orderDto.getWaterOrder().toString(),"Requested","InProgress");

//            waterOrders.stream().forEach(order1 -> {
//                if(order1.getEndTime().compareTo(startTimeStamp)<0){
//                    LOGGER.info("As your previous is in progress or requested we can not initiate a new one");
//                    return;
//                }
//            });
//
            for(Order order1 : waterOrders){
                if(order1.getEndTime().compareTo(startTimeStamp)>0){
                    LOGGER.info("As your previous is in progress or requested we can not initiate a new one");
                    return null;
                }
            }

            order.setStartTime(startTimeStamp);
            order.setEndTime(endTimeStamp);

            order.setStatus("Requested");

            order.setId(UUID.randomUUID().toString());

            LOGGER.info("new Order with order Id "+order.getId()+" of water "+order.getWaterOrder().getId()+" is Requested");

            return OrderMapper.entityToDto(orderRepository.save(order));


        }catch (Exception exception){
            exception.printStackTrace();

        }

        return null;
    }

    @Override
    public OrderDto cancelWater(UUID orderId) {
        try{


            Optional<Order> order = orderRepository.findById(orderId.toString());
            if(order.isPresent()) {
                Order order1 = order.get();
                order1.setStatus("Cancel");
                LOGGER.info("new Order with order Id "+order1.getId()+" of water "+order1.getWaterOrder().getId()+" is Cancel");

                return OrderMapper.entityToDto(orderRepository.save(order1));
            }

            return null;

        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
}
