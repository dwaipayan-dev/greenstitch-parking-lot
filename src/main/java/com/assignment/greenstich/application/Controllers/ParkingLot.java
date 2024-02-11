package com.assignment.greenstich.application.Controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.assignment.greenstich.application.Entities.Slot;
import com.assignment.greenstich.application.Entities.Vehicle;
import com.assignment.greenstich.application.Repositories.SlotRepository;
import com.assignment.greenstich.application.Repositories.VehicleRepository;
import com.assignment.greenstich.application.Services.ParkingLotManager;

public class ParkingLot {
    private static int MAX_CAPACITY = 0;

    public void initParkingLot(int maxCapacity, SlotRepository slotRepository, VehicleRepository vehicleRepository) throws Exception{
        try {
            int count = 0;
            try {
                count = slotRepository.findTotalSlots();
            } catch (Exception e) {
                
                count = 0;
            }
            if(count > 0) {
                MAX_CAPACITY = count;
                System.out.println("Sorry, cannot create a parking lot as it is not empty. Use reset to clear the parking lot first.");
            } else {
                MAX_CAPACITY = maxCapacity;
                for(int i=1; i<=MAX_CAPACITY; i++) {
                    slotRepository.save(new Slot(i));
                }
                System.out.println("Created a parking lot with " + MAX_CAPACITY + " slots");
            }
        } catch (Exception e) {
            
            throw new Exception("ParkingLot.initParkingLot failed due to " + e.getMessage());
        }
    }
    
    public void resetParkingLot(SlotRepository slotRepository, VehicleRepository vehicleRepository) throws Exception {
        try {
            vehicleRepository.dropVehicleTable();
            slotRepository.dropSlotTable();
            MAX_CAPACITY = 0;
            System.out.println("Parking lot is reset");
        } catch (Exception e) {
            
            throw new Exception("ParkingLot.resetParkingLot failed due to " + e.getMessage());
        }

    }

    public void runParkingLot() throws Exception{
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean exitFlag = false;
            SlotRepository slotRepository = new SlotRepository();
            VehicleRepository vehicleRepository = new VehicleRepository();
            ParkingLotManager parkingLotManager = new ParkingLotManager(slotRepository, vehicleRepository);
            while(!exitFlag) {
                System.out.print(">>>");
                String[] input = br.readLine().split(" ");
                switch(input[0]) {
                    case "create_parking_lot": {
                        try {
                            int maxCapacity = Integer.parseInt(input[1]);
                            initParkingLot(maxCapacity, slotRepository, vehicleRepository);
                            break;
                        } catch (Exception e) {
                            
                            System.out.println("INVALID COMMAND");
                            break;
                        } 
                    }

                    case "park": {
                        try {
                            String vehicleId = input[1];
                            String color = input[2];
                            Vehicle vehicleToPark = new Vehicle(vehicleId, color);
                            parkingLotManager.park(vehicleToPark);
                            break;
                        } catch (Exception e) {
                            
                            System.out.println("INVALID COMMAND");
                            break;
                        } 
                    }

                    case "leave": {
                        try {
                            int slotNumber = Integer.parseInt(input[1]);
                            parkingLotManager.leave(slotNumber);
                            break;
                        } catch (Exception e) {
                            
                            System.out.println("INVALID COMMAND");
                            break;
                        } 
                    }

                    case "status": {
                        try {
                            parkingLotManager.showStatus();
                            break;
                        } catch (Exception e) {
                            
                            System.out.println("INVALID COMMAND");
                            break;
                        } 
                    }

                    case "registration_numbers_for_cars_with_colour": {
                        try {
                            String color = input[1];
                            parkingLotManager.showVehiclesWithColor(color);
                            System.out.println();
                            break;
                        } catch (Exception e) {
                            
                            System.out.println("INVALID COMMAND");
                            break;
                        } 
                    }

                    case "reset": {
                        try {
                            resetParkingLot(slotRepository, vehicleRepository);
                        } catch (Exception e) {
                            
                            System.out.println("INVALID COMMAND");
                            break;
                        } 
                    }

                    case "exit": {
                        exitFlag = true;
                        break;
                    }

                    default : {
                        System.out.println("INVALID COMMAND");
                    }
                }
            }
        } catch (Exception e) {
            
            throw new Exception("ParkingLot.runParkingLot failed due to " + e.getMessage());
        }

    }
}
