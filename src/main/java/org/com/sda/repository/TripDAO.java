package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.dto.TripDTO;
import org.com.sda.entity.City;
import org.com.sda.entity.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Repository
public class TripDAO {
    public void addTrip(Trip trip) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(trip);
        transaction.commit();
        session.close();
    }

    public List<Trip> searchTrip(Trip trip, City city) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("searchTrip");
        query.setParameter("departureFlight", trip.getDepartureFlightTrip());
        query.setParameter("returnFlightId", trip.getReturnFlightTrip());
        query.setParameter("city", city);
        query.setParameter("toHotel", trip.getHotelTrip());
        List<Trip> tripList = query.getResultList();

        transaction.commit();
        session.close();

        return tripList;
    }

    public Trip findTrip(Trip trip) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("findTrip");
        query.setParameter("departureDate", trip.getDepartureFlightTrip());
        query.setParameter("returnDate", trip.getReturnFlightTrip());
        query.setParameter("hotel", trip.getHotelTrip());
        Trip trip1 = (Trip) query.getSingleResult();
        transaction.commit();
        session.close();
        return trip1;
    }

    public List<Trip> getPromotedTrips(boolean isPromoted) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("getPromotedTrips");
        query.setParameter("isPromoted", isPromoted);
        List<Trip> listOfPromotedTrips = query.getResultList();
        transaction.commit();
        session.close();
        return listOfPromotedTrips;
    }

    public List<Trip> upcomingTripsGlobally() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        long mill = System.currentTimeMillis();
        Date date = new Date(mill);
        System.out.println(date);
        Query query = session.createNamedQuery("upcomingTripsGlobally");
        query.setParameter("currentDate", date);
        List<Trip> tripList = query.getResultList();
        transaction.commit();
        session.close();
        return tripList;
    }

    public List<Trip> upcomingTripsByContinent(String continent) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("upcomingTripsByContinent");
        query.setParameter("continent", continent);
        List<Trip> tripList = query.getResultList();
        transaction.commit();
        session.close();
        return tripList;
    }
}