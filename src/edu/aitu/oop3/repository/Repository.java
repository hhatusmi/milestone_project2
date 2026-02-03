package edu.aitu.oop3.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    T create(T entity) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T entity) throws SQLException, Exception;
    void delete(int id) throws SQLException;
    void save(T entity) throws SQLException; // create или update
}

