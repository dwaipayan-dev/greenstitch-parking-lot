package com.assignment.greenstich.application.Entities;

import java.util.Base64;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    private String id;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "color")
    private String color;

    @Column(name = "has_left", nullable = false)
    private boolean hasLeft;

    @Column(name = "start_time", nullable = false)
    private Date startDate;
    
    @Column(name = "end_time")
    private Date endDate;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "slot_number")
    private Slot slot;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHasLeft() {
        return hasLeft;
    }

    public void setHasLeft(boolean hasLeft) {
        this.hasLeft = hasLeft;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Vehicle(String vehicleId, String color) {
        String combinedId = vehicleId + new Date().getTime();
        this.id = Base64.getEncoder().encodeToString(combinedId.getBytes());
        this.vehicleId = vehicleId;
        this.color = color;
        this.startDate = new Date();
        this.slot = null;
    }

    public Vehicle() {
    }
    
}
