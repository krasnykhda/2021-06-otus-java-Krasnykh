package ru.otus.crm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Nonnull;


@Table("client")
public class Client {

    @Id
    private Long id;
    @Nonnull
    private String name;


    @Nonnull
    public Client(String name) {
        this(null, name);
    }

    @PersistenceConstructor
    public Client(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
