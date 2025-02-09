package com.codeforchangeskill.OrderService.service;

import com.codeforchangeskill.OrderService.model.OrderRequest;


public interface OrderService {

    long placeOrder(OrderRequest orderRequest);

}
