package com.codeforchangeskill.OrderService.external.response;

import com.codeforchangeskill.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private long paymentId;
    private String status;
    private PaymentMode paymentMode;
    private Instant paymentDate;
    private long amount;
    private String orderId;
}
