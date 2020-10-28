package com.gnnu.schedule.service;

import com.gnnu.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> listScheduleAll();

    List<Schedule> listScheduleOrderByTime(Integer movieId);

    void deleteScheduleById(Integer scheduleId);

    void addSchedule(Schedule schedule);

    void updateSchedule(Schedule schedule);

    List<Schedule> listScheduleOrderByHallId(Integer hallId);
}
