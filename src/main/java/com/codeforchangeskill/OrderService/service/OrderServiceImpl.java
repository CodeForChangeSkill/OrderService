package com.codeforchangeskill.OrderService.service;


import com.codeforchangeskill.OrderService.entity.Order;
import com.codeforchangeskill.OrderService.model.OrderRequest;
import com.codeforchangeskill.OrderService.repository.OrderRepository;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
@Builder
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public long placeOrder(OrderRequest orderRequest) {

        //OrderEntity->Save the data with Status Order Created
        //ProductService-.Block Product(Reduce the quantity as per the available stock
        //PaymentService ->Complete Payment->Complete->Else
        //Cancelled
        // (And store the status in db)

        log.info("Placing Order Request ;{}",orderRequest);
        /*Now convert this OR into OrderEntity
         */
        Order order=Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("Created")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        order =orderRepository.save(order);
        log.info("OOrder Placed Successfully with Order ID:{}",order.getId());

        return order.getId();

    }
}
