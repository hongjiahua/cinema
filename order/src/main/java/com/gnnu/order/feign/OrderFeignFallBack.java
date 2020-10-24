package com.gnnu.order.feign;

import com.gnnu.order.entity.Seat;
import com.gnnu.order.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignFallBack implements OrderFeign {
    @Override
    public boolean updateSeat(Seat seat) {
        System.out.println("更新座位状态失败！");
        return false;
    }

    @Override
    public Seat querySeatBySeatId(Integer seatId) {
        System.out.println("查询座位失败");
        return null;
    }
}
