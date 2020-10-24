package com.gnnu.order.dao;

import com.gnnu.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface OrderDao extends JpaSpecificationExecutor<Orders>, JpaRepository<Orders, Integer>, Serializable {
    @Query("select t from Orders t order by t.orderDate")
    List<Orders> listOrdersByTime(); //根据订单时间排序

    @Query("select  t from Orders t where  t.scheduleId=?1")
    List<Orders> listOrdersByScheduleId(Integer scheduleId); //查找某一场次的所有订单

    @Query("select  t from Orders t where  t.orderId=?1")
    Orders queryOrderByOrderId(Integer orderId);

    @Query("select t from Orders t where t.uid in (select u.uid from User u where u.username=?1 )")
    Orders queryOrderByName(String username);

    @Query("select t from Orders t where t.uid=?1")
    Orders queryOrderByUId(Integer userId);

    @Query("delete from Orders t where t.orderId=?1")
    void deleteOrderById(Integer orderId);


}
