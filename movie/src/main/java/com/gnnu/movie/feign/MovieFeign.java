package com.gnnu.movie.feign;

import com.gnnu.movie.entity.Schedule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "schedule",fallback = MovieFeignFallBack.class)
public interface MovieFeign {
    @RequestMapping(value = "/schedule/listScheduleOrderByTime")
    List<Schedule> listScheduleOrderByTime(@RequestParam("movieId") Integer movieId);
}
