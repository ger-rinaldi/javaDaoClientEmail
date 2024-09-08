package org.Database;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class AbstractDaoCrud<T> implements DaoCrud<T> {
    Class<T> entityTypeT;
    EntityManager entityManager;

    public AbstractDaoCrud() {
        this.setEntityManager();
    }

    public AbstractDaoCrud(Class<T> entityTypeT) {
        this.setEntityManager();
        this.entityTypeT = entityTypeT;
    }

    public void setEntityTypeT(Class<T> entityTypeT) {
        this.entityTypeT = entityTypeT;
    }

    public Class<T> getEntityTypeT() {
        return this.entityTypeT;
    }

    private void setEntityManager() {
        this.entityManager = EntityManagerSingleton.getEntityManager();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void save(T t) {
        this.executeInTransaction(entityManager -> entityManager.persist(t));
    }

    @Override
    public void saveAll(List<T> lt) {
        this.executeInTransaction(entityManager -> lt.forEach(entityManager::persist));
    }

    @Override
    public Optional<T> getById(String idColumnName, String id) {

        String baseQueryString = "SELECT t FROM %s t WHERE t.%s = :%s";

        String formatedQueryString = String.format(baseQueryString,
                this.entityTypeT.getName(),
                idColumnName, idColumnName);

        TypedQuery<T> q = this.entityManager.createQuery(formatedQueryString,
                this.entityTypeT);

        q.setParameter(idColumnName, id);

        return Optional.ofNullable(q.getSingleResultOrNull());

    }

    @Override
    public List<T> getAll() {
        String sqlString = String.format("FROM %s ", this.entityTypeT.getName());
        Query query = this.entityManager.createQuery(sqlString);
        return query.getResultList();
    }

    @Override
    public void update(T t) {
        this.executeInTransaction(entityManager -> entityManager.merge(t));
    }

    @Override
    public void delete(T t) {
        this.update(t);
        this.executeInTransaction(entityManager -> entityManager.remove(t));
    }

    protected void executeInTransaction(Consumer<EntityManager> action) {
        EntityTransaction t = this.entityManager.getTransaction();
        try {
            t.begin();
            action.accept(this.entityManager);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
    }
}
