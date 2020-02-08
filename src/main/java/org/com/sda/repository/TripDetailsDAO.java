package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.TripDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TripDetailsDAO {
    public String buyTrip(TripDetails tripDetails){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(tripDetails);
        transaction.commit();
        session.close();
        return "Successfully purchased!";
    }
}
