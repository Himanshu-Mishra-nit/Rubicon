package com.rubicon.water.scheduler;


import com.rubicon.water.entity.Order;
import com.rubicon.water.repository.OrderRepository;
import com.rubicon.water.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SchedulerJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerJob.class);

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(fixedDelay = 60*1000L)
    public void inProgressTask(){
        List<Order> requestedOrder = orderRepository.getRequestedOrder(CommonUtils.getCurrentTime(), "Requested");
        requestedOrder.stream().forEach(requests->{
            requests.setStatus("InProgress");
            orderRepository.save(requests);
            LOGGER.info(" Water order "+requests.getId()+" of farmer "+requests.getWaterOrder().getId()+" is in Progress");
        });
    }

    @Scheduled(fixedDelay = 60*1000L)
    public void isFinishTask(){
        List<Order> progressTask = orderRepository.getProgressOrder(CommonUtils.getCurrentTime(),"InProgress");
        progressTask.stream().forEach(request->{
            request.setStatus("Delivered");
            orderRepository.save(request);
            LOGGER.info(" Water order "+request.getId()+" of farmer "+request.getWaterOrder().getId()+" is Delivered");
        });
    }
}
