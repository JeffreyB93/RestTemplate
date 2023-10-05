package org.example.repository;

public interface EntityRepository<T, K> {
    T findById(K id);

    boolean deleteById(K id);

    T save(T t);
}