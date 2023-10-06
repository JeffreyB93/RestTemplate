package org.example.service;

public interface Service<T, K> {

    T save(T t);

    T findById(K id);

    boolean deleteById(K id);
}
