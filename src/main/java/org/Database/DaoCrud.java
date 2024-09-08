package org.Database;

import java.util.List;
import java.util.Optional;

public interface DaoCrud<T> {
    void save(T t);

    void saveAll(List<T> lt);

    void update(T t);

    void delete(T t);

    Optional<T> getById(String idColumnName, String id);

    List<T> getAll();
}
