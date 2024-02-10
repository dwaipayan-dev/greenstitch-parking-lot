package com.assignment.greenstich.application;

import com.assignment.greenstich.application.Entities.Slot;
import com.assignment.greenstich.application.Enums.SlotStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.setProperty("org.jboss.logging.provider","log4j");
            System.out.println( "Hello World!" );
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager em = emf.createEntityManager();
            EntityTransaction emt = em.getTransaction();
            emt.begin();
            Slot s = new Slot(SlotStatus.AVAILABLE);
            em.persist(s);
            emt.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());;
        }
    }
}
