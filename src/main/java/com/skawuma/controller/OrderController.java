package com.skawuma.controller;

import com.skawuma.model.Order;
import com.skawuma.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/orders ")
    public Order saveOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }
}
