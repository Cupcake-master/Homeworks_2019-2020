package ru.bulat.repositories;

import java.util.List;

public interface CrudRepository<ID, V> {
    V find(String email);
    List<V> findAll();
    V save(V entity);
    void delete(V entity);
}
