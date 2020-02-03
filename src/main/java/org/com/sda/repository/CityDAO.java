package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.City;
import org.com.sda.entity.Country;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class CityDAO {
    public City findCityByNameAndCountry(String cityName, Country country) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("findCityByNameAndCountry");
        query.setParameter("cityName", cityName);
        query.setParameter("country", country);
        City city = (City) query.getSingleResult();
        transaction.commit();
        session.close();

        return city;
    }

    public void addCity(City city) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(city);
        transaction.commit();
        session.close();
    }


}
