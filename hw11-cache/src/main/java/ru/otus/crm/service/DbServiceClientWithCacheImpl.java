package ru.otus.crm.service;

import ru.otus.cachehw.HwCache;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.sessionmanager.TransactionRunner;
import ru.otus.crm.model.Client;

import java.util.List;
import java.util.Optional;

public class DbServiceClientWithCacheImpl implements DBServiceClient {
    private DBServiceClient dbServiceClient;
    private HwCache hwCache;

    public DbServiceClientWithCacheImpl(DBServiceClient dbServiceClient, HwCache hwCache) {
        this.dbServiceClient = dbServiceClient;
        this.hwCache = hwCache;
    }

    @Override
    public Client saveClient(Client client) {
        return dbServiceClient.saveClient(client);
    }

    @Override
    public Optional<Client> getClient(long id) {
        Long id2=(Long)id;
        var value = hwCache.get(id2.toString());
        if (value == null) {
            var client = dbServiceClient.getClient(id);
            hwCache.put(id2.toString(), client.get());
            return client;
        } else {
            return Optional.of((Client) value);
        }

    }

    @Override
    public List<Client> findAll() {
        return dbServiceClient.findAll();
    }
}
