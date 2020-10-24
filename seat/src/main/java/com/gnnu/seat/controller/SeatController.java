package com.gnnu.seat.controller;

import com.gnnu.seat.entity.Seat;
import com.gnnu.seat.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @RequestMapping("/listSeatByScheduleId")
    @ResponseBody
    public List<Seat> listSeatByScheduleId(@RequestParam("scheduleId") Integer scheduleId) {
        return seatService.listSeatByScheduleId(scheduleId);
    }

    @RequestMapping("/querySeatBySeatId")
    @ResponseBody
    public Seat querySeatBySeatId(@RequestParam("scheduleId") Integer scheduleId, @RequestParam("seatId") Integer seatId) {
        return seatService.querySeatBySeatId(scheduleId, seatId);
    }

    @RequestMapping("/updateSeatStatus")
    @ResponseBody
    public boolean updateSeat(Seat seat) {
        try {
            seatService.updateSeat(seat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @RequestMapping("/deleteSeat")
    @ResponseBody
    public boolean deleteSeat(Seat seat) {
        try {
            seatService.deleteSeat(seat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @RequestMapping("/addSeat")
    @ResponseBody
    public boolean addSeat(Seat seat) {
        try {
            seatService.addSeat(seat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }
}
