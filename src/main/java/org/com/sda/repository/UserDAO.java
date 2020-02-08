package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserDAO {


    public void signUp(User user){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(user);

        transaction.commit();
        session.close();
    }

//    we will search by email if the user already exists
    public User findExistingUser (String email){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNamedQuery("findExistingUser");
        query.setParameter("email",email);
        User user = (User)query.getSingleResult();
        transaction.commit();
        session.close();
        return user;
    }

    public User login(User userGiven) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("findUser");
        query.setParameter("email", userGiven.getEmail());
        query.setParameter("password", userGiven.getPassword());
        User user = (User) query.getSingleResult();

        transaction.commit();
        session.close();
        return user;
    }
}
