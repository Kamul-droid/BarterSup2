package com.jee.bartersup.dao;


import java.util.List;

public interface EntityRepository  <T>{

    void add(T t);
    boolean exists(String name);
    T findById(Long t);
    void update(T t);
    void delete(T t);
    List<T> findAll();
    List<T> findByName(String name);
}
