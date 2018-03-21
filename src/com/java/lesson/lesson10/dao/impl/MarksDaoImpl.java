package com.java.lesson.lesson10.dao.impl;

import com.java.lesson.lesson10.dao.AbstractMySQLDao;
import com.java.lesson.lesson10.dao.DaoException;
import com.java.lesson.lesson10.dao.MarksDao;
import com.java.lesson.lesson10.dto.Entity;
import com.java.lesson.lesson10.dto.Mark;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 21.02.2018.
 * Получить все предметы одного студента вместе с их оценками
 * Обновить студента, предмет, оценка
 * Добавить студента, предмет, оценку
 * Удалить студента, предмет, оценку
 */
public class MarksDaoImpl extends AbstractMySQLDao<Mark> implements MarksDao {

    public MarksDaoImpl() throws DaoException {
        this.connection = getConnection();
    }

    @Override
    protected String selectQuery() {
        return "SELECT mark_id, mark, student_id, subject_id FROM marks";
    }

    @Override
    protected String selectByIdQuery() {
        return "SELECT mark_id, mark, student_id, subject_id FROM marks WHERE mark_id = ?";
    }

    @Override
    protected String insertQuery() {
        return "INSERT INTO marks (mark_id, mark, student_id, subject_id) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String updateQuery() {
        return "INSERT INTO marks (mark_id, mark, student_id, subject_id) " +
                "VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE mark = ?, student_id = ?, subject_id = ?";
    }

    @Override
    protected String deleteQuery() {
        return "DELETE FROM marks WHERE mark_id = ?";
    }

    protected String selectSubjectsAndMarksOfStudent() {
        return "select st.f_name, st.s_name, st.course, sub.subject, m.mark from marks m " +
                "inner join students st " +
                "inner join subjects sub " +
                "on st.id = m.student_id and sub.sub_id = m.subject_id " +
                "where st.id = ?";
    }

    @Override
    protected List<Mark> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Mark> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Mark m = new Mark();
                m.setId(rs.getInt(1));
                m.setMark(rs.getInt(2));
                m.setStudentId(rs.getInt(3));
                m.setSubjectId(rs.getInt(4));
                result.add(m);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in parseResultSet method", e);
        }
        return result;
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement pst, Mark mark) throws SQLException {
        pst.setInt(1, mark.getId());
        pst.setInt(2, mark.getMark());
        pst.setInt(3, mark.getStudentId());
        pst.setInt(4, mark.getSubjectId());
        pst.setInt(5, mark.getMark());
        pst.setInt(6, mark.getStudentId());
        pst.setInt(7, mark.getSubjectId());
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement pst, Mark mark) throws SQLException {
        pst.setInt(1, mark.getId());
        pst.setInt(2, mark.getMark());
        pst.setInt(3, mark.getStudentId());
        pst.setInt(4, mark.getSubjectId());

    }

    @Override
    protected void preparedStatementForSelectById(PreparedStatement pst, int id) throws SQLException {
        pst.setInt(1, id);
    }

    @Override
    public List<Mark> getAll() throws DaoException {
        List<Mark> result;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(selectQuery());
            try (ResultSet rs = preparedStatement.executeQuery()) {
                result = parseResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in implementation of getAll method", e);
        }
        return result;
    }

    @Override
    public Mark getById(int id) throws DaoException {
        Mark mark = null;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(selectByIdQuery());
            preparedStatementForSelectById(preparedStatement, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    mark = new Mark();
                    mark.setId(rs.getInt(1));
                    mark.setMark(rs.getInt(2));
                    mark.setStudentId(rs.getInt(3));
                    mark.setSubjectId(rs.getInt(4));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getById method", e);
        }
        return mark;
    }

    public LinkedList<Entity> getSubjectsAndMarksOfStudent(int id) throws DaoException {
        LinkedList<Entity> result = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = getPreparedStatement(selectSubjectsAndMarksOfStudent());
            preparedStatementForSelectById(preparedStatement, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Entity entity = new Entity();
                    entity.setFirstName(rs.getString(1));
                    entity.setSecondName(rs.getString(2));
                    entity.setCourse(rs.getInt(3));
                    entity.setSubject(rs.getString(4));
                    entity.setMark(rs.getInt(5));
                    result.add(entity);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getSubjectsAndMarksOfStudent method", e);
        }
        return result;
    }

    @Override
    public void insert(Mark mark) throws DaoException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(insertQuery());
            preparedStatementForInsert(preparedStatement, mark);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in insert method", e);
        }
    }

    @Override
    public void update(Mark mark) throws DaoException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(updateQuery());
            preparedStatementForUpdate(preparedStatement, mark);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in update method", e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(deleteQuery());
            preparedStatementForSelectById(preparedStatement, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in delete method", e);
        }
    }
}
