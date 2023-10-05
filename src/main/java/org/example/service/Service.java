package org.example.service;

public interface Service<T, K> {

    T findById(K id);

    boolean deleteById(K id);

    T save(T t);
}
