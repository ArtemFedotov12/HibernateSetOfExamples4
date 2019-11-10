package com.start.Test4CriteriaApi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(name = "auth_book",
            joinColumns = { @JoinColumn(name = "auth_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") })
    @Fetch(value = FetchMode.SUBSELECT)
       /*@Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)*/
    private Set<Book> bookList;

    @OneToMany
    @JoinColumn(name="auth_id")
    @Fetch(value = FetchMode.SUBSELECT)
    /*
    For example response will be 40 rows -> we will have 5 queries if @BatchSize(size = 10)
    for each 10 records will be created select for related collections

    For @Fetch(value = FetchMode.SUBSELECT) it will be 2 queries

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    */
    private Set<Car> carList;


}
