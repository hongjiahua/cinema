package com.gnnu.order.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @Column(name = "Order_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Column(name = "Schedule_ID")
    private Integer scheduleId;
    @Column(name = "Seat_ID")
    private Integer seatId;
    @Column(name = "uid")
    private Integer uid;
    @Column(name = "Order_Price")
    private BigDecimal orderPrice;
    @Column(name = "Order_Date")
    private Timestamp orderDate;
    @Column(name = "Order_State")
    private String orderState;
    @Column(name = "Order_Number")
    private String orderNumber;

}
