package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.City;
import org.com.sda.entity.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HotelDAO {
    public void addHotel(Hotel hotel) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(hotel);

        transaction.commit();
        session.close();
    }

    public Hotel getHotelByCity(City city) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("getHotelByCity");
        query.setParameter("city", city);
        Hotel hotel = (Hotel) query.getSingleResult();
        transaction.commit();
        session.close();
        return hotel;
    }

    public Hotel getHotelByNameAndCity(String hotelName, City city) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("getHotelByNameAndCity");
        query.setParameter("hotelName",hotelName);
        query.setParameter("city", city);
        Hotel hotel = (Hotel)query.getSingleResult();
        transaction.commit();
        session.close();
        return hotel;
    }

}
