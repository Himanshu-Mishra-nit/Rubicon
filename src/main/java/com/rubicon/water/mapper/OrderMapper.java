package com.rubicon.water.mapper;

import com.rubicon.water.dto.ImmutableOrderDto;
import com.rubicon.water.dto.OrderDto;
import com.rubicon.water.entity.Order;
import com.rubicon.water.util.CommonUtils;

import java.util.UUID;

public interface OrderMapper {

        public static OrderDto entityToDto(Order order){
           try {
               return ImmutableOrderDto.builder()
                       .id(UUID.fromString(order.getId()))
                       .startTime(CommonUtils.convertTimeStamp(order.getStartTime(), "yyyy-MM-dd HH:mm:ss"))
                       .duration(CommonUtils.duration(order.getStartTime(), order.getEndTime()))
                        .status(order.getStatus())
                       .waterOrder(UUID.fromString(order.getWaterOrder().getId()))
                       .build();
           }catch (Exception e){e.printStackTrace();
           return null;}
        }

}
