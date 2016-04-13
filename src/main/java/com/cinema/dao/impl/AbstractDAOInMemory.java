package com.cinema.dao.impl;


import com.cinema.dao.api.Dao;
import com.cinema.dao.storage.InMemoryDB;
import com.cinema.exception.UserLoginException;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDAOInMemory<T, K> implements Dao<T, K> {
    private Class<T> persistentClass;

    public AbstractDAOInMemory(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public void create(T t) {
        InMemoryDB instance = InMemoryDB.getInstance();
        instance.create(t);

    }

    public List<T> getAll() {
        Class entity = getPersistentClass();
        InMemoryDB instance = InMemoryDB.getInstance();
        return instance.getAll(entity);
    }

    public T update(T t) {
        InMemoryDB instance = InMemoryDB.getInstance();
        return (T) instance.update(t);
    }

    public void delete(K id) {
        Class entity = getPersistentClass();
        InMemoryDB instance = InMemoryDB.getInstance();
        instance.delete(entity, id);
    }

    public T get(K id) {
        Class entity = getPersistentClass();
        InMemoryDB instance = InMemoryDB.getInstance();
        return (T) instance.get(entity, id);
    }

    private Class<T> getPersistentClass() {
        return persistentClass;
    }
}
