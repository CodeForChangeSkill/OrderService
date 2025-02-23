package com.codeforchangeskill.OrderService.service;

import com.codeforchangeskill.OrderService.model.OrderRequest;
import com.codeforchangeskill.OrderService.model.OrderResponse;


public interface OrderService {



    long placeOrder(OrderRequest orderRequest);


    OrderResponse getOrderDetails(long orderId);

}
