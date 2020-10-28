package com.gnnu.movie.feign;

import com.gnnu.movie.entity.Schedule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieFeignFallBack implements  MovieFeign  {
    @Override
    public List<Schedule> listScheduleOrderByTime(Integer movieId) {
        System.out.println("远程调用安排模块失败");
        return null;
    }
}
