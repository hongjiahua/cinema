package com.gnnu.order.service.impl;

import com.gnnu.order.dao.OrderDao;
import com.gnnu.order.entity.Orders;
import com.gnnu.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    @CachePut(value = "listOrdersByTime")
    public List<Orders> listOrdersByTime() {
        return orderDao.listOrdersByTime();
    }

    @Override
    @CachePut(value = "listOrdersByScheduleId", key = "#scheduleId")
    public List<Orders> listOrdersByScheduleId(Integer scheduleId) {
        return orderDao.listOrdersByScheduleId(scheduleId);
    }

    @Override
    @CachePut(value = "queryOrderByOrderId", key = "#orderId")
    public Orders queryOrderByOrderId(Integer orderId) {
        return orderDao.queryOrderByOrderId(orderId);
    }

    @Override
    @CachePut(value = "queryOrderByUId", key = "#userId")
    public Orders queryOrderByUId(Integer userId) {
        return orderDao.queryOrderByUId(userId);
    }

    @Override
    @CachePut(value = "queryOrderByName", key = "#username")
    public Orders queryOrderByName(String username) {
        return orderDao.queryOrderByName(username);
    }

    @Override
    @Transactional
    @Modifying
    @CacheEvict(value = {"listOrdersByTime","listOrdersByScheduleId","queryOrderByOrderId","queryOrderByName"})
    public void deleteOrderById(Integer orderId) {
        orderDao.deleteOrderById(orderId);
    }

    @Override
    @Transactional
    @Modifying
    public void addOrder(Orders order) {
        orderDao.save(order);
    }

    @Override
    @Transactional
    @Modifying
    @CacheEvict(value = {"listOrdersByTime","listOrdersByScheduleId","queryOrderByOrderId","queryOrderByName"})
    public void updateOrder(Orders order) {
        orderDao.save(order);
    }
}
