package com.assignment.greenstich.application.Repositories;

import java.util.List;

import com.assignment.greenstich.application.Entities.Slot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class SlotRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    
    public void save(Slot slot) throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            EntityTransaction emt = em.getTransaction();
            emt.begin();
            em.persist(slot);
            emt.commit();
            em.close();
        } catch (Exception e) {
            throw new Exception("SlotRepository.save() failed due to" + e.getMessage());
        }
    }

    public void update(Slot slot) throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            EntityTransaction emt = em.getTransaction();
            emt.begin();
            em.merge(slot);
            emt.commit();
            em.close();
        } catch (Exception e) {
            throw new Exception("SlotRepository.save() failed due to" + e.getMessage());
        }
    }

    public Slot findBySlotNumber(int slotNumber) throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            Slot slot = em.find(Slot.class, slotNumber);
            em.close();
            return slot;
        } catch (Exception e) {
            throw new Exception("SlotRepository.findBySlotNumber() failed due to: " + e.getMessage());
        }
    }

    public Slot findNextAvailableSlot() throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            String queryString = "SELECT s FROM Slot s WHERE s.status = 'AVAILABLE' ORDER BY s.slotNumber LIMIT 1";
            TypedQuery<Slot> query = em.createQuery(queryString, Slot.class);
            List<Slot> results = (List<Slot>)query.getResultList();
            em.close();
            if(results.isEmpty()) return null;
            return results.get(0);
        } catch (Exception e) {
            throw new Exception("SlotRepository.findNextAvailableSlot() failed due to: " + e.getMessage());
        }
    }

    public int findTotalSlots() throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            String queryString = "SELECT COUNT(*) FROM Slot";
            Query query = em.createQuery(queryString, Slot.class);
            long results = (long) query.getSingleResult();
            em.close();
            return (int)results;
        } catch (Exception e) {
            throw new Exception("SlotRepository.findNextAvailableSlot() failed due to: " + e.getMessage());
        }
    }

    public void dropSlotTable() throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            String dropQuery = "DROP TABLE IF EXISTS slot";
            em.createNativeQuery(dropQuery).executeUpdate();
            et.commit();
            em.close();
        } catch (Exception e) {
            throw new Exception("SlotRepository.dropSlotTable() failed due to: " + e.getMessage());
        }
    }
}
