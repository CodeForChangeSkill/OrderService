package com.codeforchangeskill.OrderService.controller;

import com.codeforchangeskill.OrderService.external.client.ProductService;
import com.codeforchangeskill.OrderService.model.OrderRequest;
import com.codeforchangeskill.OrderService.model.OrderResponse;
import com.codeforchangeskill.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){

        long orderId=orderService.placeOrder(orderRequest);
        log.info("OrderId :{}",orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
    // Here we will be using RESTTEMPLATE instead of FEIGNCLIENT to get order details

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){

        //Remember it shows function need to be static issue if in place of orderService its class OrderService is added;
        OrderResponse orderResponse
                =orderService.getOrderDetails(orderId);


        return new ResponseEntity<>(orderResponse,
                HttpStatus.OK);
    }


}
