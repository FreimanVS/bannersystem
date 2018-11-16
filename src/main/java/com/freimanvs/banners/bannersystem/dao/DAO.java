package com.freimanvs.banners.bannersystem.dao;

import java.util.List;

public interface DAO<T> {
    T getById(Long id);
    List<T> getAll();
    boolean remove(Long id);
    boolean update(Long id, T o);
    long add(T o);
}
