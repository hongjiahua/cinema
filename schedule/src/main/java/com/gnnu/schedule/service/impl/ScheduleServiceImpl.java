package com.gnnu.schedule.service.impl;

import com.gnnu.schedule.dao.ScheduleDao;
import com.gnnu.schedule.entity.Schedule;
import com.gnnu.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    @CachePut(value = "listScheduleOrderByTime", key = "#movieId")
    public List<Schedule> listScheduleOrderByTime(Integer movieId) {
        return scheduleDao.listScheduleOrderByTime(movieId);
    }

    @Override
    @Transactional
    @Modifying
    public void deleteScheduleById(Integer scheduleId) {
        scheduleDao.deleteScheduleById(scheduleId);
    }

    @Override
    @Transactional
    @Modifying
    public void addSchedule(Schedule schedule) {
        scheduleDao.save(schedule);
    }
}
