package com.start.util;


import com.start.Test1.entity.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    //Property based configuration
    private static SessionFactory sessionJavaConfigFactory;


    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();

            //Create Properties, can be read from property files too
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost/hibernate_set_of_examples?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "root");
            props.put("hibernate.current_session_context_class", "thread");
            props.put("hibernate.hbm2ddl.auto", "update");
            props.put("hibernate.show_sql", "true");
            configuration.setProperties(props);

            //we can set mapping file or class with annotation
            //addClass(Employee1.class) will look for resource
            // com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
            //configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(com.start.Test2HQL.Employee.class);
            configuration.addAnnotatedClass(com.start.Test2HQL.Address.class);

            configuration.addAnnotatedClass(com.start.Test4CriteriaApi.Author.class);
            configuration.addAnnotatedClass(com.start.Test4CriteriaApi.Book.class);
            configuration.addAnnotatedClass(com.start.Test4CriteriaApi.Car.class);
            configuration.addAnnotatedClass(com.start.Test4CriteriaApi.Reader.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static SessionFactory getSessionJavaConfigFactory() {
        if (sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }

}
