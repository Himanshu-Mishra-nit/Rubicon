package com.rubicon.water.entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="orders")
public class Order{

    @Id
    @Column(name="id")
    private String id;

    @Column(name="start_Time")
    private Timestamp startTime;

    @Column(name="end_Time")
    private Timestamp endTime;

    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "water")
    private WaterOrder waterOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WaterOrder getWaterOrder() {
        return waterOrder;
    }

    public void setWaterOrder(WaterOrder waterOrder) {
        this.waterOrder = waterOrder;
    }
}
