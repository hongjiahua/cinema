package com.gnnu.order.controller;

import com.gnnu.order.entity.Orders;
import com.gnnu.order.entity.Seat;
import com.gnnu.order.feign.OrderFeign;
import com.gnnu.order.service.OrderService;
import com.gnnu.order.util.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("/listOrdersByTime")
    @ResponseBody
    public List<Orders> listOrdersByTime() {
        return orderService.listOrdersByTime();
    }

    @RequestMapping("/listOrdersByScheduleId")
    @ResponseBody
    public List<Orders> listOrdersByScheduleId(@RequestParam("scheduleId") Integer scheduleId) {
        return orderService.listOrdersByScheduleId(scheduleId);
    }

    @RequestMapping("/queryOrderByOrderId")
    @ResponseBody
    public Orders queryOrderByOrderId(@RequestParam("orderId") Integer orderId) {
        return orderService.queryOrderByOrderId(orderId);
    }

    @RequestMapping("/queryOrderByName")
    @ResponseBody
    public Orders queryOrderByName(@RequestParam("username") String username) {
        return orderService.queryOrderByName(username);
    }

    @RequestMapping("/queryOrderByUId")
    @ResponseBody
    public Orders queryOrderByUId(@RequestParam("userId") Integer userId) {
        return orderService.queryOrderByUId(userId);
    }

    @RequestMapping("/deleteOrderById")
    @ResponseBody
    public boolean deleteOrderById(@RequestParam("orderId") Integer orderId) {
        try {
            Orders order = orderService.queryOrderByOrderId(orderId);
            Integer seatId = order.getSeatId();
            Seat seat = orderFeign.querySeatBySeatId(seatId);
            seat.setSeatStatus("unselected");
            seat.setSeatSelect("no");
            orderFeign.updateSeat(seat);
            orderService.deleteOrderById(orderId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public boolean addOrder(Orders order, @RequestParam("seatId") Integer seatId) {
        try {

            String orderId = GenerateUUID.getUUID();
            order.setOrderNumber(orderId);
            Seat seat = orderFeign.querySeatBySeatId(seatId);
            seat.setSeatSelect("yes");
            seat.setSeatStatus("selected");
            orderFeign.updateSeat(seat);
            orderService.addOrder(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("/updateOrder")
    @ResponseBody
    public boolean updateOrder(Orders order) {
        try {
            orderService.updateOrder(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}

