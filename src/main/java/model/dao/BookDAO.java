package model.dao;


import model.entity.Book;
import utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class BookDAO {

    public static void insert(Book book) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
    }

    public static Book searchById(int id) {
        Transaction transaction = null;
        Book book = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        book = session.get(Book.class, id);
        transaction.commit();

        return book;
    }

    public static void deleteById(int id) {
        Transaction transaction = null;
        Book book = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        book = session.get(Book.class, id);
        session.delete(book);
        transaction.commit();
    }

    public static void update(Book book) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(book);
        transaction.commit();
    }

    public static List<Book> searchByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = session.createQuery("FROM Book e WHERE e.title = " + name).getResultList();
        transaction.commit();

        return books;
    }

    public static List<Book> searchAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = null;
        books = session.createQuery("FROM Book").list();

        return books;
    }
}
