package com.gnnu.order.service;

import com.gnnu.order.entity.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> listOrdersByTime(); //根据订单时间排序

    List<Orders> listOrdersByScheduleId(Integer scheduleId); //查找某一场次的所有订单

    Orders queryOrderByOrderId(Integer orderId);

    Orders queryOrderByUId(Integer userId);

    Orders queryOrderByName(String username);

    void deleteOrderById(Integer orderId);

    void addOrder(Orders order);

    void updateOrder(Orders order);


}
