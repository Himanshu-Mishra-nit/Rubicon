package com.rubicon.water.controller;

import com.rubicon.water.dto.OrderDto;
import com.rubicon.water.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public ResponseEntity<?> newOrder(@RequestBody OrderDto orderDto){
        try{
            OrderDto orderDto1= orderService.newOrder(orderDto);
            if(orderDto1 == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As your previous is in progress or requested we can not initiate a new one");
            }
            return ResponseEntity.ok(orderDto1);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/cancel/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable("orderId") UUID orderId){
        try{

           OrderDto orderDto = orderService.cancelWater(orderId);
            if(orderDto==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.ok(orderDto);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
