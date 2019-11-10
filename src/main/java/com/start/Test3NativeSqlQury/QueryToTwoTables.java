package com.start.Test3NativeSqlQury;

import com.start.Test2HQL.Address;
import com.start.Test2HQL.Employee;
import com.start.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;


public class QueryToTwoTables {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        NativeQuery query = session.createSQLQuery(
                "select e.emp_id, e.emp_name, e.emp_salary," +
                " a.address_line1, a.zipcode, " +
                " a.city from Employee e, Address a where a.emp_id=e.emp_id");

                List<Object[]> rows = query.list();

        for(Object[] row : rows){
            Employee emp = new Employee();
            emp.setId(Long.parseLong(row[0].toString()));
            emp.setName(row[1].toString());
            emp.setSalary(Double.parseDouble(row[2].toString()));
            com.start.Test2HQL.Address address = new Address();
            address.setAddressLine1(row[3].toString());
            address.setCity(row[4].toString());
            address.setZipcode(row[5].toString());
            emp.setAddress(address);
            System.out.println(emp);
        }

    }
}
