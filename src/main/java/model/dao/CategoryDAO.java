package model.dao;

import model.entity.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class CategoryDAO {

    public static void insert(Category category) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
    }

    public static Category searchById(int id) {
        Transaction transaction = null;
        Category category = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        category = session.get(Category.class, id);
        transaction.commit();

        return category;
    }

    public static void deleteById(int id) {
        Transaction transaction = null;
        Category category = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        category = session.get(Category.class, id);
        session.delete(category);
        transaction.commit();
    }

    public static void update(Category category) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(category);
        transaction.commit();
    }

    public static List<Category> searchByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Category> users = session.createQuery("FROM Category e WHERE e.name = " + name).getResultList();
        transaction.commit();

        return users;
    }

    public static List<Category> searchAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Category> categories = null;
        categories = session.createQuery("FROM Category").list();

        return categories;
    }
}
