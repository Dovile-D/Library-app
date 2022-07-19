package model.dao;

import model.entity.Book;
import model.entity.Category;
import model.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.BCryptPassword;
import utils.HibernateUtil;
import org.hibernate.Session;

public class UserDAO {

    public UserDAO() {
    }

    public void register(String regUsername, String regEmail, boolean isAdmin, String regPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String password = BCryptPassword.hashPassword(regPassword);
        User user = new User(regUsername, regEmail, isAdmin, password);
        session.save(user);
        session.getTransaction().commit();
        System.out.println("Register successful!");
    }

    public boolean login(String logUsername, String logPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User findUserFDB = this.findUserByUsername(logUsername);
        session.getTransaction().commit();
        if (logUsername.equals(findUserFDB.getName())) {
            if (BCryptPassword.checkPassword(logPassword, findUserFDB.getPassword())) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Login unsuccessful! Wrong password!");
                return false;
            }
        } else {
            System.out.println("Login unsuccessful! Wrong username!");
            return false;
        }
    }

    public boolean getUserRole(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = this.findUserByUsername(name);
        session.getTransaction().commit();
        return user.getIsAdmin();
    }

    public User findUserByUsername(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Object user = session.createQuery("select a from User a where a.name = '" + name + "'").getSingleResult();
        session.getTransaction().commit();
        return (User)user;
    }

    public static User searchById(int id) {
        Transaction transaction = null;
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        user = session.get(User.class, id);
        transaction.commit();

        return user;
    }

    public static void insert(User user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }
}
