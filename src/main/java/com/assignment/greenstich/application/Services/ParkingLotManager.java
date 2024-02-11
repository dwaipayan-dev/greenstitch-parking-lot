package com.assignment.greenstich.application.Services;

import java.util.Date;
import java.util.List;

import com.assignment.greenstich.application.Entities.Slot;
import com.assignment.greenstich.application.Entities.Vehicle;
import com.assignment.greenstich.application.Enums.SlotStatus;
import com.assignment.greenstich.application.Repositories.SlotRepository;
import com.assignment.greenstich.application.Repositories.VehicleRepository;

public class ParkingLotManager {
    private SlotRepository slotRepository;
    private VehicleRepository vehicleRepository;
    
    public ParkingLotManager(SlotRepository slotRepository, VehicleRepository vehicleRepository) {
        this.slotRepository = slotRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public void park(Vehicle vehicle) throws Exception {
        try {
            Slot nextSlot = slotRepository.findNextAvailableSlot();
            if(nextSlot == null) {
                System.out.println("Sorry, parking lot is full");
            } else {
                nextSlot.setStatus(SlotStatus.BUSY);
                slotRepository.update(nextSlot);
                vehicle.setSlot(nextSlot);
                vehicleRepository.save(vehicle);
                System.out.println("Allocated slot number: " + nextSlot.getSlotNumber());
            }
        } catch (Exception e) {
            
            throw new Exception("ParkingLotManager.park failed due to " + e.getMessage());
        }
        
    }
    public void leave(int slotNumber) throws Exception {
        try {
            Slot slotToBeVacated = slotRepository.findBySlotNumber(slotNumber);
            if(slotToBeVacated == null) {
                System.out.println("Sorry, slot number is invalid");
            } else {
                if(slotToBeVacated.getStatus() != SlotStatus.BUSY) {
                    System.out.println("Sorry, the slot is already vacant");
                    return;
                }
                Vehicle  vehicleInTheSlot = vehicleRepository.findBySlot(slotNumber);
                if(vehicleInTheSlot == null) {
                    throw new Exception ("DATA MISMATCH!!");
                } else {
                    vehicleInTheSlot.setHasLeft(true);
                    vehicleInTheSlot.setEndDate(new Date());
                    slotToBeVacated.setStatus(SlotStatus.AVAILABLE);
                    vehicleInTheSlot.setSlot(slotToBeVacated);
                    vehicleRepository.update(vehicleInTheSlot);
                    System.out.println("Slot number " + slotToBeVacated.getSlotNumber() + " is free");
                }
            }
        } catch (Exception e) {
            
            throw new Exception("ParkingLotManager.leave failed due to " + e.getMessage());
        }
        
    }
    public void showStatus() throws Exception{
        try {
            List<Vehicle> statusResult = vehicleRepository.findAllParkedVehicles();
            System.out.printf("%10s %20s %10s", "Slot No.", "Registration No.", "Color");
            System.out.println();
            for(Vehicle vehicleStatus: statusResult) {
                System.out.printf("%10s %20s %10s", vehicleStatus.getSlot().getSlotNumber(), vehicleStatus.getVehicleId(), vehicleStatus.getColor());
                System.out.println();
            }
            
        } catch (Exception e) {
            
            throw new Exception("ParkingLotManager.showStatus failed due to " + e.getMessage());
        }
    }
    public void showVehiclesWithColor(String color) throws Exception{
        try {
            List<Vehicle>  vehiclesWithGivenColor = vehicleRepository.findVehiclesByColor(color);
            for(int i = 0; i < vehiclesWithGivenColor.size(); i++) {
                System.out.print(vehiclesWithGivenColor.get(i).getVehicleId());
                if(i < vehiclesWithGivenColor.size() - 1) {
                    System.out.print(", ");
                }
            }
        } catch (Exception e) {
            
            throw new Exception("ParkingLotManager.showVehiclesWithColor failed due to " + e.getMessage());
        }
    };
}
