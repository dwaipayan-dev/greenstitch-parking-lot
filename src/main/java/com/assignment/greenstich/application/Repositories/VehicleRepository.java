package com.assignment.greenstich.application.Repositories;

import java.util.List;
import com.assignment.greenstich.application.Entities.Vehicle;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class VehicleRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    
    public void save(Vehicle vehicle) throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            EntityTransaction emt = em.getTransaction();
            emt.begin();
            em.persist(vehicle);
            emt.commit();
            em.close();
        } catch (Exception e) {
            throw new Exception("VehicleRepository.save() failed due to" + e.getMessage());
        }
    }

    public void update(Vehicle vehicle) throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            EntityTransaction emt = em.getTransaction();
            emt.begin();
            em.merge(vehicle);
            emt.commit();
            em.close();
        } catch (Exception e) {
            throw new Exception("VehicleRepository.save() failed due to" + e.getMessage());
        }
    }

    public Vehicle findBySlot(int slotNumber) throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            String queryString = "SELECT v from Vehicle v WHERE v.slot.slotNumber = :slotNumber";
            TypedQuery<Vehicle> query = em.createQuery(queryString, Vehicle.class);
            query.setParameter("slotNumber", slotNumber);
            List<Vehicle> results = (List<Vehicle>)query.getResultList();
            em.close();
            if(results.isEmpty()) {
                return null;
            } else {
                return results.get(0);
            }
            
        } catch (Exception e) {
            
            throw new Exception("VehicleRepository.findBySlot() failed due to" + e.getMessage());
        }
    }

    public List<Vehicle> findAllParkedVehicles() throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            String queryString = "SELECT v from Vehicle v WHERE v.hasLeft = false";
            TypedQuery<Vehicle> query = em.createQuery(queryString, Vehicle.class);
            List<Vehicle> results = (List<Vehicle>)query.getResultList();
            em.close();
            return results;
        } catch (Exception e) {
            
            throw new Exception("VehicleRepository.findAllParkedVehicles failed due to " + e.getMessage());
        }
    }

    public List<Vehicle> findVehiclesByColor(String color) throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            String queryString = "SELECT v from Vehicle v WHERE v.color = :color AND v.hasLeft=false";
            TypedQuery<Vehicle> query = em.createQuery(queryString, Vehicle.class);
            query.setParameter("color", color);
            List<Vehicle> results = (List<Vehicle>) query.getResultList();
            em.close();
            return results;
        } catch (Exception e) {
            
            throw new Exception("VehicleRepository.findVehiclesByColor failed due to " + e.getMessage());
        }
    }

    public void dropVehicleTable() throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            String dropQuery = "DROP TABLE IF EXISTS vehicle";
            em.createNativeQuery(dropQuery).executeUpdate();
            et.commit();
            em.close();
        } catch (Exception e) {
            throw new Exception("VehicleRepository.dropVehicleTable() failed due to: " + e.getMessage());
        }
    }
}
