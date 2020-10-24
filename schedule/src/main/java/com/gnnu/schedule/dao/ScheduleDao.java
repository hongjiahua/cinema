package com.gnnu.schedule.dao;

import com.gnnu.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ScheduleDao extends JpaRepository<Schedule, Integer>, JpaSpecificationExecutor<Schedule>, Serializable {
    @Query("select t from Schedule t where t.movieId=?1 order by t.scheduleBeginDateTime desc")
    List<Schedule> listScheduleOrderByTime(Integer movieId);
    @Query("delete from  Schedule t where t.scheduleId=?1")
    void deleteScheduleById(Integer scheduleId);
}
