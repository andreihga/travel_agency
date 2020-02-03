package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TripDAO {
    public void addTrip(Trip trip){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(trip);
        transaction.commit();
        session.close();
    }

    public List<Trip> searchTrip(Trip trip){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("searchTrip");
        query.setParameter("departureFlight", trip.getDepartureFlightId());
        query.setParameter("returnFlightId", trip.getReturnFlightId());
        query.setParameter("city", trip.getHotelId().getCity());
        query.setParameter("toHotel",trip.getHotelId());
        List<Trip> tripList = query.getResultList();

        transaction.commit();
        session.close();

        return tripList;
    }
}