package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.Airport;
import org.com.sda.entity.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AirportDAO {
    public void addAirport(Airport airport){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(airport);
        transaction.commit();
        session.close();
    }

    public List<Airport> getAirports(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("getAirports");
        List<Airport> airportList = query.getResultList();
        transaction.commit();
        session.close();
        return airportList;
    }

    public Airport getAirportByNameAndCity(String airportName, City city){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("getAirportByNameAndCity");
        query.setParameter("airportName", airportName);
        query.setParameter("city",city);
        Airport airport = (Airport) query.getSingleResult();
        transaction.commit();
        session.close();

        return airport;
    }

}
