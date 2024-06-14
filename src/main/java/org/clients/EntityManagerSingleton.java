package org.clients;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.secrets.Secrets;

import java.util.HashMap;
import java.util.Map;

public class EntityManagerSingleton {
    private static EntityManager entityManager = null;

    public EntityManagerSingleton() {
        super();
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory entityManagerFactory =
                    createEntityManagerFactoryWithEnvSecrets();
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    private static Map<String, String> getEnvironmentSecrets(){
        Map<String, String> configOverrides = new HashMap<>();

        configOverrides.put("jakarta.persistence.jdbc.user", Secrets.DB_USER.label);
        configOverrides.put("jakarta.persistence.jdbc.password", Secrets.DB_PASSWORD.label);

        return configOverrides;
    }

    private static EntityManagerFactory createEntityManagerFactoryWithEnvSecrets(){
        return Persistence.createEntityManagerFactory("UnitClients",
                                                      getEnvironmentSecrets());
    }
}
