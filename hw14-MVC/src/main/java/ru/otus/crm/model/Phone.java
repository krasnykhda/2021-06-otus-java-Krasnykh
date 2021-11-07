package ru.otus.crm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Nonnull;

@Table("phone")
public class Phone {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }


    public String getNumber() {
        return number;
    }


    public Long getClientId() {
        return clientId;
    }

    @Nonnull
    private String number;

    @PersistenceConstructor
    public Phone(Long id, String number, Long clientId) {
        this.id = id;
        this.number = number;
        this.clientId = clientId;
    }

    @Nonnull
    public Phone(String number, Long clientId) {
        this(null, number, clientId);
    }

    @Nonnull
    private Long clientId;
}
