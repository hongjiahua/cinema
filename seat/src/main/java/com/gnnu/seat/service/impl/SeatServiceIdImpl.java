package com.gnnu.seat.service.impl;

import com.gnnu.seat.dao.SeatDao;
import com.gnnu.seat.entity.Seat;
import com.gnnu.seat.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SeatServiceIdImpl implements SeatService {
    @Autowired
    private SeatDao seatDao;

    @Override
    public List<Seat> listSeatByScheduleId(Integer scheduleId) {
        return seatDao.listSeatByScheduleId(scheduleId);
    }

    @Override
    public Seat querySeatBySeatId(Integer scheduleId, Integer seatId) {
        return seatDao.querySeatBySeatId(scheduleId, seatId);
    }

    @Override
    @Modifying
    @Transactional
    public void updateSeat(Seat seat) {
        seatDao.save(seat);
    }

    @Override
    public void deleteSeat(Seat seat) {
        seatDao.delete(seat);
    }

    @Override
    public void addSeat(Seat seat) {
        seatDao.save(seat);
    }
}
