package com.codeforchangeskill.OrderService.service;


import com.codeforchangeskill.OrderService.entity.Order;
import com.codeforchangeskill.OrderService.exception.CustomException;
import com.codeforchangeskill.OrderService.external.client.PaymentService;
import com.codeforchangeskill.OrderService.external.client.ProductService;
import com.codeforchangeskill.OrderService.external.request.PaymentRequest;
import com.codeforchangeskill.OrderService.model.OrderRequest;
import com.codeforchangeskill.OrderService.model.OrderResponse;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;


    @Override
    public long placeOrder(OrderRequest orderRequest) {

        //OrderEntity->Save the data with Status Order Created
        //ProductService-.Block Product(Reduce the quantity as per the available stock
        //PaymentService ->Complete Payment->Complete->Else
        //Cancelled
        // (And store the status in db)

        log.info("Placing Order Request ;{}",orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        log.info("Created order with status CREATED");

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

        //PaymentService ->Complete Payment->Complete->Else

        log.info("Calling PaymentService to complete the payment");
        PaymentRequest paymentRequest
                =PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

            String orderStatus=null;
            try {
                paymentService.doPayment(paymentRequest);
                log.info("Payment done successfully.Changing the OrderStatus to placed");
                        orderStatus="PLACED";
            } catch (Exception e)
            {
            log.error("Error occurred in Payment.Changing OrderStatus to payment failed");
                    orderStatus="PAYMENT_FAILED";
            }
            order.setOrderStatus(orderStatus);
            orderRepository.save(order);

            log.info("Order Placed Successfully with Order ID:{}", order.getId());
            return order.getId();
        }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for OrderId :{}",orderId);

        Order order=
                orderRepository.findById(orderId)
                .orElseThrow(()->new CustomException("Order not Found",
                        "ORDER_NOT_FOUND",
                        404));

        OrderResponse orderResponse
                = OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .amount(order.getAmount())
                .build();

        return orderResponse;



    }
}
