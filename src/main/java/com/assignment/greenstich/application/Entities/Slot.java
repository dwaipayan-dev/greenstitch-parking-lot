package com.assignment.greenstich.application.Entities;

import com.assignment.greenstich.application.Enums.SlotStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "slot")
public class Slot {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    public int getId() {
        return id;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Slot [id=" + id + ", status=" + status + "]";
    }

    public Slot(SlotStatus status) {
        this.status = status;
    }

}
