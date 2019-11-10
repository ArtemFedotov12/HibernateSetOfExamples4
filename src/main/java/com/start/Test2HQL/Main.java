package com.start.Test2HQL;

import com.start.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.getCurrentSession();

        Transaction tx = session.beginTransaction();
        Query query  = session.createQuery("delete from Employee where id= :id");
        query.setLong("id", 4);
        int result = query.executeUpdate();
        System.out.println("Employee Update Status="+result);
        tx.commit();

        session.close();
    }
}
