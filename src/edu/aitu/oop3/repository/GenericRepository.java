package edu.aitu.oop3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericRepository<T> implements Repository<T> {
    private List<T> items = new ArrayList<>();

    @Override
    public void save(T item) {
        items.add(item);
    }

    @Override
    public List<T> findAll() {
        return items;
    }

    @Override
    public void update(T entity) throws Exception {

    }

    @Override
    public T create(T entity) throws Exception {
        return null;
    }

    @Override
    public T findById(int id) {
        return (T) Optional.empty();
    }

    @Override
    public void delete(int id) {
    }
}
