package com.start.Test5Cache;

import com.start.Test2HQL.Employee;
import com.start.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateFirstLevelCache {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Employee emp = (Employee) session.load(Employee.class, 1L);
        System.out.println("---------" + emp.getId() +emp.getName());

        //Here won't be fired any query to db. It will be taken from Cache First Level
        Employee emp2 = (Employee) session.load(Employee.class, 1L);
        System.out.println("---------" + emp2.getId() +emp2.getName());

        Employee emp3 = (Employee) session.load(Employee.class, 3L);
        System.out.println("---------" + emp3.getId() +emp3.getName());


    }
}
