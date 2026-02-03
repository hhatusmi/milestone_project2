package edu.aitu.oop3.repository;

import java.util.List;

public interface Repository<T> {
    T create(T entity) throws Exception;
    T findById(int id) throws Exception;
    List<T> findAll() throws Exception;
    void update(T entity) throws Exception;
    void delete(int id) throws Exception;
}
