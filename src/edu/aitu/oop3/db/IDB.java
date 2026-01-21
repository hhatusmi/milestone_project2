package edu.aitu.oop3.db;

public interface IDB {
    void connect();
    void disconnect();
    void create(Object entity);
    Object read(int id);
    void update(Object entity);
    void delete(int id);
}

