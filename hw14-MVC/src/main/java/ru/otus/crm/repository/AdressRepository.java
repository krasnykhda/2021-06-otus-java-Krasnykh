package ru.otus.crm.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.crm.model.Adress;
import ru.otus.crm.model.Client;


public interface AdressRepository extends CrudRepository<Adress, Long> {
}
