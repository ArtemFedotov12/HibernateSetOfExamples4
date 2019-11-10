package com.start.Test3NativeSqlQury;

import com.start.Test2HQL.Employee;
import com.start.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import java.util.List;

public class NativeSql {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.getCurrentSession();

// Get All Employees
        Transaction tx = session.beginTransaction();
        /**
         * from performance point of view
         * we can use addScalar() method to define the data type of the column.
         */
        NativeQuery query = session.createSQLQuery("select emp_id, emp_name, emp_salary from Employee")
                .addScalar("emp_id", new LongType())
                .addScalar("emp_name", new StringType())
                .addScalar("emp_salary", new DoubleType());
        List<Object[]> rows = query.list();
        for(Object[] row : rows){
            Employee emp = new Employee();
            emp.setId(Long.parseLong(row[0].toString()));
            emp.setName(row[1].toString());
            emp.setSalary(Double.parseDouble(row[2].toString()));
            System.out.println(emp);
            tx.commit();
        }
    }
}
