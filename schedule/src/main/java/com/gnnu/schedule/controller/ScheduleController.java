package com.gnnu.schedule.controller;

import com.gnnu.schedule.entity.Movie;
import com.gnnu.schedule.entity.Schedule;
import com.gnnu.schedule.service.ScheduleService;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/schedule")
@CrossOrigin("*")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/listScheduleAll")
    @ResponseBody
    public List<Schedule> listScheduleAll(){
        return scheduleService.listScheduleAll();
    }

    @RequestMapping("/listScheduleOrderByTime")
    @ResponseBody
    public List<Schedule> listScheduleOrderByTime(@RequestParam("movieId") Integer movieId) {
        System.out.println(movieId);
        System.out.println(scheduleService.listScheduleOrderByTime(movieId));
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

    @RequestMapping("/addSchedule")
    @ResponseBody
    public boolean addSchedule(
            @RequestParam("movieId")Integer movieId,
            @RequestParam("hallId")Integer hallId,
            @RequestParam("schedulePrice")BigDecimal schedulePrice,
            @RequestParam("scheduleBeginDateTime") Timestamp scheduleBeginDateTime,
            @RequestParam("scheduleEndDateTime") Timestamp scheduleEndDateTime
    ) {
        Schedule schedule=new Schedule();
        Movie movie=new Movie();
        movie.setMovieId(movieId);
        schedule.setMovie(movie);
        schedule.setHallId(hallId);
        schedule.setSchedulePrice(schedulePrice);
        schedule.setScheduleBeginDateTime(scheduleBeginDateTime);
        schedule.setScheduleEndDateTime(scheduleEndDateTime);
        List<Schedule> schedules=scheduleService.listScheduleOrderByHallId(hallId);
        if(scheduleBeginDateTime.before(scheduleEndDateTime)) {
            for (Schedule sch :
                    schedules) {
                if ((scheduleBeginDateTime.before(sch.getScheduleBeginDateTime()) && scheduleEndDateTime.after(sch.getScheduleBeginDateTime())) || (scheduleBeginDateTime.after(sch.getScheduleBeginDateTime()) && scheduleEndDateTime.before(sch.getScheduleEndDateTime())) || (scheduleBeginDateTime.before(sch.getScheduleBeginDateTime()) && scheduleEndDateTime.after(sch.getScheduleBeginDateTime()))) {
                    return false;
                }
            }

            try {
                scheduleService.addSchedule(schedule);
                return true;
            } catch (Exception exception) {
                exception.printStackTrace();
                return false;
            }
        }else{
            return false;
        }

    }

    @RequestMapping("/updateSchedule")
    @ResponseBody
    public boolean updateSchedule(@RequestParam("scheduleId")Integer scheduleId,
                                  @RequestParam("movieId")Integer movieId,
                                  @RequestParam("hallId")Integer hallId,
                                  @RequestParam("schedulePrice")BigDecimal schedulePrice,
                                  @RequestParam("scheduleBeginDateTime") Timestamp scheduleBeginDateTime,
                                  @RequestParam("scheduleEndDateTime") Timestamp scheduleEndDateTime){
        try{
            System.out.println(scheduleEndDateTime);
            Schedule schedule=new Schedule();
            Movie movie=new Movie();
            movie.setMovieId(movieId);
            schedule.setScheduleId(scheduleId);
            schedule.setMovie(movie);
            schedule.setHallId(hallId);
            schedule.setSchedulePrice(schedulePrice);
            schedule.setScheduleBeginDateTime(scheduleBeginDateTime);
            schedule.setScheduleEndDateTime(scheduleEndDateTime);
            System.out.println(schedule.getScheduleEndDateTime());
            scheduleService.updateSchedule(schedule);
            return true;
        }catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
