package com.gnnu.order.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Seat_ID")
    private int seatId;
    @Column(name = "Hall_ID")
    private Integer hallId;
    @Column(name = "Seat_Row")
    private Integer seatRow;
    @Column(name = "Seat_Column")
    private Integer seatColumn;
    @Column(name = "Seat_Status")
    private String seatStatus;
    @Column(name = "Seat_Select")
    private String seatSelect;
    @Column(name = "Schedule_ID")
    private Integer scheduleId;
}
