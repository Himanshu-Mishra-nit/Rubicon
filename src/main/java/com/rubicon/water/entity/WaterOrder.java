package com.rubicon.water.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="water_order")
public class WaterOrder {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "waterOrder")
    private List<Order> orderList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
