package ru.otus;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.core.repository.executor.DbExecutorImpl;
import ru.otus.core.sessionmanager.TransactionRunner;
import ru.otus.core.sessionmanager.TransactionRunnerJdbc;
import ru.otus.crm.datasource.DriverManagerDataSource;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Manager;
import ru.otus.crm.service.DbServiceClientImpl;
import ru.otus.crm.service.DbServiceClientWithCacheImpl;
import ru.otus.crm.service.DbServiceManagerImpl;
import ru.otus.jdbc.mapper.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeWork {
    private static final String URL = "jdbc:postgresql://localhost:5432/demoDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";
    private static final Logger log = LoggerFactory.getLogger(HomeWork.class);
    private static DriverManagerDataSource dataSource;
    private static DbExecutor dbExecutor;
    private static TransactionRunner transactionRunner;
    private static EntityClassMetaData<Client> entityClassMetaDataClient;
    private static EntitySQLMetaData<Client> entitySQLMetaDataClient;
    private static DataTemplate dataTemplateClient;
    private static List<Long> listId = new ArrayList<>();

    private static void getClientWithoutCache() {
        var dbServiceClient = new DbServiceClientImpl(transactionRunner, dataTemplateClient);
        long start = new Date().getTime();
        for (Long i : listId) {
            var clientSecondSelected = dbServiceClient.getClient(i)
                    .orElseThrow(() -> new RuntimeException("Client not found, id:" + i));
        }
        for (Long i : listId) {
            var clientSecondSelected = dbServiceClient.getClient(i)
                    .orElseThrow(() -> new RuntimeException("Client not found, id:" + i));
        }
        long end = new Date().getTime();
        log.info("time without cache:{}", end - start);
    }

    private static void getClientWithCache() {
        var dbServiceClient = new DbServiceClientImpl(transactionRunner, dataTemplateClient);
        HwCache<Long, Client> cache = new MyCache<>();
        var dbServiceClientWithCache = new DbServiceClientWithCacheImpl(dbServiceClient, cache);
        long start = new Date().getTime();
        for (Long i : listId) {
            var clientSecondSelected = dbServiceClientWithCache.getClient(i)
                    .orElseThrow(() -> new RuntimeException("Client not found, id:" + i));
        }
        for (Long i : listId) {
            var clientSecondSelected = dbServiceClientWithCache.getClient(i)
                    .orElseThrow(() -> new RuntimeException("Client not found, id:" + i));
        }
        long end = new Date().getTime();
        log.info("time with cache:{}", end - start);
    }

    public static void addClient(int count) {
        var dbServiceClient = new DbServiceClientImpl(transactionRunner, dataTemplateClient);
        for (int i = 0; i <= count; i++) {
            var client = dbServiceClient.saveClient(new Client("Client" + i));
            listId.add(client.getId());
        }
    }

    public static void main(String[] args) {
        setupDataBaseConnection();
        addClient(100);
        getClientWithoutCache();
        getClientWithCache();
    }

    public static void setupDataBaseConnection() {
        dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(dataSource);
        transactionRunner = new TransactionRunnerJdbc(dataSource);
        dbExecutor = new DbExecutorImpl();
        entityClassMetaDataClient = new EntityClassMetaDataImpl<>(Client.class);
        entitySQLMetaDataClient = new EntitySQLMetaDataImpl<>(entityClassMetaDataClient);
        dataTemplateClient = new DataTemplateJdbc<Client>(dbExecutor, entitySQLMetaDataClient); //реализация Da
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
