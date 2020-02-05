package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.Flight;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Repository
public class FlightDAO {

    public void addFlight(Flight flight){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(flight);

        transaction.commit();
        session.close();
    }

    public Flight getFlightByFlightNumber(String flightNumber){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("getFlightByFlightNumber");
        query.setParameter("flightNumber",flightNumber);
        Flight flight = (Flight) query.getSingleResult();
        transaction.commit();
        session.close();
        return flight;
    }

    public Flight getFlightByDepartureDate(Date departureDate){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("getFlightByDepartureDate");
        query.setParameter("departureDate",departureDate);
        Flight flight = (Flight)query.getSingleResult();
        transaction.commit();
        session.close();
        return flight;
    }
}
