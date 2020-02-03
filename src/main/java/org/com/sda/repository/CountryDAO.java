package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
public class CountryDAO {
    public List<Country> getCountries() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("getCountries");
        List<Country> countryList = query.getResultList();
        transaction.commit();
        session.close();
        return countryList;
    }

    public void addCountry(Country country){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(country);
        transaction.commit();
        session.close();
    }

    public Country findCountryByName(String countryName){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("getCountryByName");
        query.setParameter("countryName",countryName);
        Country country = (Country) query.getSingleResult();

        transaction.commit();
        session.close();

        return country;
    }


}
