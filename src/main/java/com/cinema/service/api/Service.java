package com.cinema.service.api;

import com.cinema.exception.UserLoginException;

import java.util.List;

public interface Service<T, K> {
    List<T> getAll();
    void create(T t);
    T get(K id);
    T update(T t);
    void delete(K id);
}
