package com.assignment.greenstich.application.Entities;

import com.assignment.greenstich.application.Enums.SlotStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "slot")
public class Slot {
    @Id
    @Column(name = "slot_number")
    private int slotNumber;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }


    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String toString() {
        return "Slot [slotNumber=" + slotNumber + ", status=" + status + "]";
    }

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.status = SlotStatus.AVAILABLE;
    }

    public Slot() {
    }

}
