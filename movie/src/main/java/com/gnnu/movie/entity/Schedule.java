package com.gnnu.movie.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "schedule")
@Data
public class Schedule implements Serializable{
    @Id
    @Column(name = "Schedule_ID")
    private int scheduleId;
    @Column(name = "Movie_ID")
    private Integer movieId;
    @Column(name = "Hall_ID")
    private Integer hallId;
    @Column(name = "Schedule_Price")
    private BigDecimal schedulePrice;
    @Column(name = "Schedule_BeginDateTime")
    private Timestamp scheduleBeginDateTime;
    @Column(name = "Schedule_EndDateTime")
    private Timestamp scheduleEndDateTime;

}