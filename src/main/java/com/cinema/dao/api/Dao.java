package com.cinema.dao.api;

import java.util.List;

public interface Dao<T, K> {
    List<T> getAll();
    void create(T t);
    T update(T t);
    void delete(K id);
    T get(K id);
}
