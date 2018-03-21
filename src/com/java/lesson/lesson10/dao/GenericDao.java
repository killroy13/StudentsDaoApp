package com.java.lesson.lesson10.dao;

/**
 * Created by User on 28.02.2018.
 */
public interface GenericDao<T> {

    void insert(T t) throws DaoException;

    void update(T t) throws DaoException;

    void delete(int id) throws DaoException;

}
