package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.RoomAvailability;
import org.com.sda.entity.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class RoomAvailabilityDAO {
    public void addRoom(RoomAvailability room) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(room);

        transaction.commit();
        session.close();
    }

    public RoomAvailability seeAvailability(Trip trip) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("getRoomAvailability");
        query.setParameter("fromDate", trip.getDepartureDateHotel());
        query.setParameter("toDate", trip.getReturnDateHotel());
        query.setParameter("hotel", trip.getHotelTrip());
        RoomAvailability roomsAvailability = (RoomAvailability) query.getSingleResult();
        transaction.commit();
        session.close();
        return roomsAvailability;
    }
}
