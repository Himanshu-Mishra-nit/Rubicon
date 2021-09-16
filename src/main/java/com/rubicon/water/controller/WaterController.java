package com.rubicon.water.controller;

import com.rubicon.water.dto.WaterOrderDto;
import com.rubicon.water.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/water")
public class WaterController {

    @Autowired
    private WaterService waterService;

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseEntity<?> newUser(@RequestBody WaterOrderDto waterOrderDto){
        try{
            WaterOrderDto waterOrderDto1= waterService.createOrder(waterOrderDto);
            return ResponseEntity.ok(waterOrderDto1);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
