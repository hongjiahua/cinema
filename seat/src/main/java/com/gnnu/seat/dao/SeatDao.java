package com.gnnu.seat.dao;

import com.gnnu.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface SeatDao extends JpaRepository<Seat, Integer>, JpaSpecificationExecutor<Seat>, Serializable {
    @Query("select t from Seat t where t.scheduleId=?1")
    List<Seat> listSeatByScheduleId(Integer scheduleId);

    @Query("select t from Seat t where t.scheduleId=?1 and  t.seatId=?2")
    Seat querySeatBySeatId(Integer scheduleId, Integer seatId);
}
