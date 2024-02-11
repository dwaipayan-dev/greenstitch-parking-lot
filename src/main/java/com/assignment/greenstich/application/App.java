package com.assignment.greenstich.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.assignment.greenstich.application.Controllers.ParkingLot;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Logger.getLogger("org.hibernate").setLevel(Level.OFF);
            System.out.println("Welcome to parking lot application...");
            
            ParkingLot myParkingLot = new ParkingLot();
            myParkingLot.runParkingLot();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());;
        }
    }
}
