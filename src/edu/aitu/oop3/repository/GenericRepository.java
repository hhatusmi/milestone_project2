package edu.aitu.oop3.repository;

import edu.aitu.oop3.entities.Identifiable;

import java.util.ArrayList;
import java.util.List;

public class GenericRepository<T extends Identifiable> implements Repository<T> {
    private final List<T> items = new ArrayList<>();
    private int currentId = 1;

    @Override
    public T create(T entity) {
        entity.setId(currentId++);
        items.add(entity);
        return entity;
    }

    @Override
    public void save(T entity) {
        if (entity.getId() == 0) {
            create(entity);
        } else {
            update(entity);
        }
    }

    @Override
    public void update(T entity) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == entity.getId()) {
                items.set(i, entity);
                return;
            }
        }
    }

    @Override
    public T findById(int id) {
        for (T item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public void delete(int id) {
        items.removeIf(item -> item.getId() == id);
    }
}