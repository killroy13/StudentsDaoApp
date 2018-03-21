package com.java.lesson.lesson10.dao;

import com.java.lesson.lesson10.dto.Student;

import java.util.List;

/**
 * Created by User on 14.02.2018.
 */
public interface StudentsDao extends GenericDao<Student> {

    List<Student> getAll() throws DaoException;

    Student getById(int id) throws DaoException;

}








