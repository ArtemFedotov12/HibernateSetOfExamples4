package com.start.Test4CriteriaApi;

import com.start.Test2HQL.Address;
import com.start.Test2HQL.Employee;
import com.start.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

//https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-hibernate-5/src/main/java/com/baeldung/hibernate/criteria/view/ApplicationView.java

public class CriteriaApi {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();


        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
        Root<Employee> root = cr.from(Employee.class);
        Fetch<Employee, Address> join = root.fetch("address");

        //cr.multiselect(join.get("address"));


        Expression doubleExpression = root.get("salary");
        Predicate predicate =  cb.between(doubleExpression, 100, 400);

        cr.select(root)
                .where(predicate);

        Query<Employee> query = session.createQuery(cr);
        List<Employee> results = query.getResultList();

        for(Employee emp : results){
            System.out.println("ID="+emp.getId()+", Salary="+emp.getSalary()+", Zipcode="+emp.getAddress().getZipcode());
        }


    }
}
