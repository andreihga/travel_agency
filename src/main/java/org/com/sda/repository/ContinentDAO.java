package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.Continent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ContinentDAO {
    public Continent getContinentByName(String continentName){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("getContinents");
        query.setParameter("continentName",continentName);
        Continent continent = (Continent) query.getSingleResult();
        transaction.commit();
        session.close();

        return continent;
    }
    public void addContinent(Continent continent){

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(continent);

        transaction.commit();
        session.close();
    }



}
