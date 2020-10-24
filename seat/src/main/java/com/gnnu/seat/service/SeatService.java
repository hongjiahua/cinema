package com.gnnu.seat.service;

import com.gnnu.seat.entity.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> listSeatByScheduleId(Integer scheduleId);

    Seat querySeatBySeatId(Integer scheduleId, Integer seatId);

    void updateSeat(Seat seat);

    void deleteSeat(Seat seat);

    void addSeat(Seat seat);
}
