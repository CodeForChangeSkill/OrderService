package com.codeforchangeskill.OrderService.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Entity
@Table(name="ORDER_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(name ="PRODUCT_ID")
        private long productId;

        @Column(name ="Quantity")
        private long quantity;

        @Column(name ="ORDER_DATE")
        private Instant orderDate;

        @Column(name ="STATUS")
        private String orderStatus;

        @Column(name ="TOTAL_AMOUNT")
        private long amount;

}

