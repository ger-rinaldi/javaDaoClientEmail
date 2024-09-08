package org.clients;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.Enviroment.DotenvSingleton;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.Map;

public class EntityManagerSingleton {
    private static EntityManager entityManager = null;

    public EntityManagerSingleton() {
        super();
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory entityManagerFactory = createEntityManagerFactoryWithEnvSecrets();
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    private static Map<String, String> getEnvironmentSecrets() {
        Map<String, String> configOverrides = new HashMap<>();

        Dotenv dotenv = DotenvSingleton.getDotenv();

        configOverrides.put("jakarta.persistence.jdbc.user", dotenv.get("MYSQL_USER"));
        configOverrides.put("jakarta.persistence.jdbc.password", dotenv.get("MYSQL_PASSWORD"));
        configOverrides.put("jakarta.persistence.jdbc.url", dotenv.get("JDBC_URL") + dotenv.get("MYSQL_DATABASE"));

        return configOverrides;
    }

    private static EntityManagerFactory createEntityManagerFactoryWithEnvSecrets() {
        return Persistence.createEntityManagerFactory("UnitClients",
                getEnvironmentSecrets());
    }
}
