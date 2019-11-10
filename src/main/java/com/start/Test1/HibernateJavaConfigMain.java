package com.start.Test1;

import com.start.Test1.entity.Employee;
import com.start.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateJavaConfigMain {
    public static void main(String[] args) {

        Employee emp = new Employee();
        emp.setName("Lisa");

        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(emp);

        session.getTransaction().commit();

        System.out.println("Employee ID="+emp.getId());
        //terminate session factory, otherwise program won't end
        sessionFactory.close();
    }
}
