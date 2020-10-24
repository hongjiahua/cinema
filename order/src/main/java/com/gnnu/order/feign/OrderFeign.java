package com.gnnu.order.feign;

import com.gnnu.order.entity.Seat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "seat", fallback = OrderFeignFallBack.class)
public interface OrderFeign {
    @RequestMapping("/seat/updateSeatStatus")
    boolean updateSeat(Seat seat);

    @RequestMapping("/seat/querySeatBySeatId")
    Seat querySeatBySeatId(Integer seatId);
}
