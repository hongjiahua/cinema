package com.gnnu.schedule.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Schedule {
    private int scheduleId;
    private Integer movieId;
    private Integer hallId;
    private BigDecimal schedulePrice;
    private Timestamp scheduleBeginDateTime;
    private Timestamp scheduleEndDateTime;

    @Id
    @Column(name = "Schedule_ID")
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Basic
    @Column(name = "Movie_ID")
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "Hall_ID")
    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    @Basic
    @Column(name = "Schedule_Price")
    public BigDecimal getSchedulePrice() {
        return schedulePrice;
    }

    public void setSchedulePrice(BigDecimal schedulePrice) {
        this.schedulePrice = schedulePrice;
    }

    @Basic
    @Column(name = "Schedule_BeginDateTime")
    public Timestamp getScheduleBeginDateTime() {
        return scheduleBeginDateTime;
    }

    public void setScheduleBeginDateTime(Timestamp scheduleBeginDateTime) {
        this.scheduleBeginDateTime = scheduleBeginDateTime;
    }

    @Basic
    @Column(name = "Schedule_EndDateTime")
    public Timestamp getScheduleEndDateTime() {
        return scheduleEndDateTime;
    }

    public void setScheduleEndDateTime(Timestamp scheduleEndDateTime) {
        this.scheduleEndDateTime = scheduleEndDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return scheduleId == schedule.scheduleId &&
                Objects.equals(movieId, schedule.movieId) &&
                Objects.equals(hallId, schedule.hallId) &&
                Objects.equals(schedulePrice, schedule.schedulePrice) &&
                Objects.equals(scheduleBeginDateTime, schedule.scheduleBeginDateTime) &&
                Objects.equals(scheduleEndDateTime, schedule.scheduleEndDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, movieId, hallId, schedulePrice, scheduleBeginDateTime, scheduleEndDateTime);
    }
}
