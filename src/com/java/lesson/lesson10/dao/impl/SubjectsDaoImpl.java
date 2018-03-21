package com.java.lesson.lesson10.dao.impl;

import com.java.lesson.lesson10.dao.AbstractMySQLDao;
import com.java.lesson.lesson10.dao.DaoException;
import com.java.lesson.lesson10.dao.SubjectsDao;
import com.java.lesson.lesson10.dto.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 20.02.2018.
 */
public class SubjectsDaoImpl extends AbstractMySQLDao<Subject> implements SubjectsDao {

    public SubjectsDaoImpl() throws DaoException {
        this.connection = getConnection();
    }

    @Override
    protected String selectQuery() {
        return "SELECT sub_id, subject FROM subjects";
    }

    @Override
    protected String selectByIdQuery() {
        return "SELECT sub_id, subject FROM subjects WHERE sub_id = ?";
    }

    @Override
    protected String insertQuery() {
        return "INSERT INTO subjects (sub_id, subject) VALUES (?, ?)";
    }

    @Override
    protected String updateQuery() {
        return "INSERT INTO subjects (sub_id, subject) VALUES (?, ?) ON DUPLICATE KEY UPDATE subject = ?";
    }

    protected String forDeleteQuery() {
        return "DELETE FROM marks WHERE subject_id = ?";
    }

    @Override
    protected String deleteQuery() {
        return "DELETE FROM subjects WHERE sub_id = ?";
    }

    @Override
    protected List<Subject> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Subject> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getInt(1));
                s.setSubject(rs.getString(2));
                result.add(s);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in parseResultSet method", e);
        }
        return result;
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement pst, Subject subject) throws SQLException {
        pst.setInt(1, subject.getId());
        pst.setString(2, subject.getSubject());
        pst.setString(3, subject.getSubject());
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement pst, Subject subject) throws SQLException {
        pst.setInt(1, subject.getId());
        pst.setString(2, subject.getSubject());
    }

    @Override
    protected void preparedStatementForSelectById(PreparedStatement pst, int id) throws SQLException {
        pst.setInt(1, id);
    }

    @Override
    public List<Subject> getAll() throws DaoException {
        List<Subject> result;
        try {
            PreparedStatement statement = getPreparedStatement(selectQuery());
            try (ResultSet rs = statement.executeQuery()) {
                result = parseResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in implementation of getAll method", e);
        }
        return result;
    }

    @Override
    public Subject getById(int id) throws DaoException {
        Subject subject = null;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(selectByIdQuery());
            preparedStatementForSelectById(preparedStatement, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    subject = new Subject();
                    subject.setId(rs.getInt(1));
                    subject.setSubject(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getById method", e);
        }
        return subject;
    }

    @Override
    public void insert(Subject subject) throws DaoException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(insertQuery());
            preparedStatementForInsert(preparedStatement, subject);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in insert method", e);
        }
    }

    @Override
    public void update(Subject subject) throws DaoException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(updateQuery());
            preparedStatementForUpdate(preparedStatement, subject);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in update method", e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(forDeleteQuery());
            preparedStatementForSelectById(preparedStatement, id);
            preparedStatement.executeUpdate();
            preparedStatement = getPreparedStatement(deleteQuery());
            preparedStatementForSelectById(preparedStatement, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in delete method", e);
        }
    }
}
