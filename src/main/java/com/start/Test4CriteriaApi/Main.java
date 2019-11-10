package com.start.Test4CriteriaApi;

import com.start.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import java.util.List;

//Solution of N+1 Problem
public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();


        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> cr = cb.createQuery(Author.class);
        Root<Author> root = cr.from(Author.class);
        Fetch<Author, Book> join = root.fetch("bookList");
       // Fetch<Book, Reader> join2 = join.fetch("readerList");

        cr.select(root);

        Query<Author> query = session.createQuery(cr);
        List<Author> results = query.getResultList();

        for(Author author : results){
            System.out.println("ID="+author.getId()
                    +", Name="+author.getName()
                    +", BookList= "+author.getBookList()
                    +", CarList= "+author.getCarList());
        }


    }
}
