package com.rubicon.water.repository;

import com.rubicon.water.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("select order from Order order where order.startTime<=?1 and order.endTime>?1 and order.status=?2")
    public List<Order> getRequestedOrder(Timestamp currentTime, String status);

    @Query("select order from Order order where order.endTime<=?1 and order.status=?2")
    public List<Order> getProgressOrder(Timestamp currentTime, String status);

    @Query("select order from Order order where order.waterOrder.id=?1 and order.status in (?2,?3)")
    public List<Order> getAllOrders(String waterId, String requested, String inProgress);

}
