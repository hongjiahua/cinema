package com.gnnu.schedule.dao;

import com.gnnu.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ScheduleDao extends JpaRepository<Schedule, Integer>, JpaSpecificationExecutor<Schedule>, Serializable {
    @Query("select t from Schedule t where t.movie.movieId=?1 order by t.scheduleBeginDateTime desc")
    List<Schedule> listScheduleOrderByTime(Integer movieId);

    @Query("select t from Schedule t where t.hallId=?1")
    List<Schedule> listScheduleOrderByHallId(Integer hallId);
    @Modifying
    @Query(value="delete from  schedule  where Schedule_ID= ?1",nativeQuery = true)
    void deleteScheduleById(Integer scheduleId);

    @Query("select t from  Schedule t join  Movie m on t.movie.movieId=m.movieId")
    List<Schedule> listScheduleAll();
}
