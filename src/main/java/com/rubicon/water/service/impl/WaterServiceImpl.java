package com.rubicon.water.service.impl;

import com.rubicon.water.dto.WaterOrderDto;
import com.rubicon.water.entity.WaterOrder;
import com.rubicon.water.mapper.WaterOrderMapper;
import com.rubicon.water.repository.WaterRepository;
import com.rubicon.water.service.WaterService;
import com.rubicon.water.util.CommonUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class WaterServiceImpl implements WaterService {



    @Autowired
    private WaterRepository waterRepository;

    @Override
    public WaterOrderDto createOrder(WaterOrderDto waterOrderDto) {
       try{
           WaterOrder waterOrder = new WaterOrder();
           BeanUtils.copyProperties(waterOrder, waterOrderDto);
           waterOrder.setId(UUID.randomUUID().toString());

           return WaterOrderMapper.entityToDto(waterRepository.save(waterOrder));

       }catch (Exception exception){
           exception.printStackTrace();
           return null;
       }
    }

}
