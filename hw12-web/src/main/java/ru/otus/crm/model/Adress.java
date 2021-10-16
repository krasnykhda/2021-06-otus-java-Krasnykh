package ru.otus.crm.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "adress")
@NoArgsConstructor

public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public Adress(String street) {
        this.id = null;
        this.street = street;
    }

    public Adress(Long id, String name) {
        this.id = id;
        this.street = name;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", street='" + street + '\'' +
                '}';
    }
}
