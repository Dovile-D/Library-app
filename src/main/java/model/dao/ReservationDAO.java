package model.dao;

import model.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class ReservationDAO {

    public static void insert(Reservation reservation) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(reservation);
        transaction.commit();
    }

}
