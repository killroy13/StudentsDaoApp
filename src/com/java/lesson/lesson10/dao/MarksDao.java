package com.java.lesson.lesson10.dao;

import com.java.lesson.lesson10.dto.Mark;

import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public interface MarksDao extends GenericDao<Mark> {

    List<Mark> getAll() throws DaoException;

    Mark getById(int id) throws DaoException;

//    Mark getSubjectsAndMarksOfStudent (int id) throws DaoException;

//    boolean insert(Mark mark) throws DaoException;
//
//    boolean update(Mark mark) throws DaoException;
//
//    boolean deleteById(int id) throws DaoException;
}
