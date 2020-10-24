package com.gnnu.schedule.controller;

import com.gnnu.schedule.entity.Schedule;
import com.gnnu.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/schedule")
@CrossOrigin("*")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/listScheduleOrderByTime")
    @ResponseBody
    public List<Schedule> listScheduleOrderByTime(@RequestParam("movieId") Integer movieId) {
        return scheduleService.listScheduleOrderByTime(movieId);
    }

    @RequestMapping("/deleteScheduleById")
    @ResponseBody
    public boolean deleteScheduleById(@RequestParam("scheduleId") Integer scheduleId) {
        try {
            scheduleService.deleteScheduleById(scheduleId);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

    }

    @RequestMapping("/addScheduleId")
    @ResponseBody
    public boolean deleteScheduleById(Schedule schedule) {
        try {
            scheduleService.addSchedule(schedule);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

    }
}
