package com.rubicon.water.service;

import com.rubicon.water.dto.WaterOrderDto;

import javax.transaction.Transactional;
import java.util.UUID;

public interface WaterService {

    @Transactional
    public WaterOrderDto createOrder(WaterOrderDto waterOrderDto);


}
