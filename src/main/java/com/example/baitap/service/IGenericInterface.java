package com.example.baitap.service;

import java.util.List;

public interface IGenericInterface<E> {
    List<E> finAll();
    E finOne(int id);
    void update(E e);
    void create(E e);
    void remove(int id);
}
