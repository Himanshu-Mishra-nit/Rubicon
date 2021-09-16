package com.rubicon.water.mapper;

import com.rubicon.water.dto.ImmutableWaterOrderDto;
import com.rubicon.water.dto.WaterOrderDto;
import com.rubicon.water.entity.WaterOrder;

import java.util.UUID;

public interface WaterOrderMapper {

    public static WaterOrderDto entityToDto(WaterOrder waterOrder){
        if(waterOrder==null)
            return null;
        try {
            return ImmutableWaterOrderDto.builder()
                    .id(UUID.fromString(waterOrder.getId()))
                    .name(waterOrder.getName())
                    .build();
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }

}
