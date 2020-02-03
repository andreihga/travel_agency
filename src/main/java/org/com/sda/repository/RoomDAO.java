package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDAO {
    public void addRoom(Room room){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(room);

        transaction.commit();
        session.close();
    }
}
