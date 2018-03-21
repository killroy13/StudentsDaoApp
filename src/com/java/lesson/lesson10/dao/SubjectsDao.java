package com.java.lesson.lesson10.dao;

import com.java.lesson.lesson10.dto.Subject;

import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public interface SubjectsDao extends GenericDao<Subject> {

    List<Subject> getAll() throws DaoException;

    Subject getById(int id) throws DaoException;



//    boolean insert(Subject subject) throws DaoException;
//    boolean update(Subject subject) throws DaoException;
//    boolean deleteById(int id) throws DaoException;

}
