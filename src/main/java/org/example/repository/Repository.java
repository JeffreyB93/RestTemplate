package org.example.repository;

public interface Repository<T, K> {
    T findById(K id);

    boolean deleteById(K id);

    T save(T t);
}