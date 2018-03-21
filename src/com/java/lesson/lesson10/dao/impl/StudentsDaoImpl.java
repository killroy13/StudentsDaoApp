package com.java.lesson.lesson10.dao.impl;

import com.java.lesson.lesson10.dao.AbstractMySQLDao;
import com.java.lesson.lesson10.dao.DaoException;
import com.java.lesson.lesson10.dao.StudentsDao;
import com.java.lesson.lesson10.dto.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 14.02.2018.
 */
public class StudentsDaoImpl extends AbstractMySQLDao<Student> implements StudentsDao {

    public StudentsDaoImpl() throws DaoException {
        this.connection = getConnection();
    }

    @Override
    protected String selectQuery() {
        return "SELECT id, f_name, s_name, course FROM students";
    }

    @Override
    protected String selectByIdQuery() {
        return "SELECT id, f_name, s_name, course FROM students WHERE id = ?";
    }

    @Override
    protected String insertQuery() {
        return "INSERT INTO students (id, f_name, s_name, course) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String updateQuery() {
        return "INSERT INTO students (id, f_name, s_name, course) VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE f_name = ?, s_name = ?, course = ?";
    }

    @Override
    protected String deleteQuery() {
        return "DELETE FROM students WHERE id = ?";
    }

    protected String forDeleteQuery() {
        return "DELETE FROM marks WHERE student_id = ?";
    }

    @Override
    protected List<Student> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Student> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt(1));
                s.setFirstName(rs.getString(2));
                s.setSecondName(rs.getString(3));
                s.setCourse(rs.getInt(4));
                result.add(s);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in parseResultSet method", e);
        }
        return result;
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement pst, Student student) throws SQLException {
        pst.setInt(1, student.getId());
        pst.setString(2, student.getFirstName());
        pst.setString(3, student.getSecondName());
        pst.setInt(4, student.getCourse());
        pst.setString(5, student.getFirstName());
        pst.setString(6, student.getSecondName());
        pst.setInt(7, student.getCourse());
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement pst, Student student) throws SQLException {
        pst.setInt(1, student.getId());
        pst.setString(2, student.getFirstName());
        pst.setString(3, student.getSecondName());
        pst.setInt(4, student.getCourse());
    }

    @Override
    protected void preparedStatementForSelectById(PreparedStatement pst, int id) throws SQLException {
        pst.setInt(1, id);
    }

    @Override
    public List<Student> getAll() throws DaoException {
        List<Student> result;
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
    public Student getById(int id) throws DaoException {
        Student student = null;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(selectByIdQuery());
            preparedStatementForSelectById(preparedStatement, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt(1));
                    student.setFirstName(rs.getString(2));
                    student.setSecondName(rs.getString(3));
                    student.setCourse(rs.getInt(4));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getById method", e);
        }
        return student;
    }

    @Override
    public void insert(Student student) throws DaoException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(insertQuery());
            preparedStatementForInsert(preparedStatement, student);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in insert method", e);
        }
    }

    @Override
    public void update(Student student) throws DaoException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(updateQuery());
            preparedStatementForUpdate(preparedStatement, student);
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




/** Закрытие PreparedStatement и Connection*/
//    public void close() throws DaoException {
////        Map<PreparedStatement, Exception> map = new HashMap<>();
//        List<SQLException> list = new ArrayList<>();
//        for (PreparedStatement preparedStatement : preparedStatements.values()) {
//            try {
//                if (preparedStatement != null) {
////                    throw new SQLException();
//                    preparedStatement.close();
////                    System.out.println("close");
//                }
//
//            } catch (SQLException e) {
////                map.put(preparedStatement, e);
//                list.add(e);
////                System.out.println(" add in list");
//            }
//        }
//        if (connection != null) {
//            try {
//                connection.close();
////                System.out.println(" CONN CLOS");
//            } catch (SQLException e) {
//                list.add(e);
//            }
//        }
//        Exception ex = new Exception();
//        if (!list.isEmpty()) {
//            for (SQLException e : list) {
//                ex.addSuppressed(e);
//            }
//            throw new DaoException("Error in close DAO", ex);
//        }
//    }


/** Создание PreparedStatement*/
//    protected PreparedStatement getPreparedStatement(String sql) throws DaoException {
//        PreparedStatement preparedStatement = preparedStatements.get(sql);
//        if (preparedStatement == null) {
//            try {
//                preparedStatement = connection.prepareStatement(sql);
//                preparedStatements.put(sql, preparedStatement);
//            } catch (SQLException e) {
//                throw new DaoException(e);
//            }
//        }
//        return preparedStatement;
//    }


//    private PreparedStatement getSelectPS;
//
//    private PreparedStatement getSelectByIdPS;
//
//    private PreparedStatement getInsertPS;
//
//    private PreparedStatement getUpdatePS;
//
//    private PreparedStatement getDeleteByIdPS;
//
//    private PreparedStatement getForDeleteByIdPS;


/**
 * в Select в SelectById  В Delete  В update В insert Создание PS
 * Закрытие до
 * <p>
 * //    public void close(AutoCloseable resource) throws DaoException {
 * //        try {
 * //            if (resource != null) {
 * //                resource.close();
 * //                System.out.println(" is closed ");
 * //            }
 * //        } catch (Exception e) {
 * //            throw new DaoException(e);
 * //        }
 * //    }
 * //
 * //    public void close1(AutoCloseable resource) throws DaoException {
 * //        try {
 * //            if (resource != null) {
 * //                resource.close();
 * //                throw new DaoException("Error in " + resource + " method");
 * //            }
 * //        } catch (Exception e) {
 * //            System.err.println("Error in close method");
 * //            throw new DaoException(e);
 * //        }
 * //    }
 * //
 * //    public void close() throws DaoException {
 * //        close(getSelectPS);
 * //        close1(getSelectByIdPS);
 * //        close(getInsertPS);
 * //        close(getUpdatePS);
 * //        close(getDeleteByIdPS);
 * //        close(getForDeleteByIdPS);
 * //        close(connection);
 * //    }
 */
//        List<Student> list;
//        ResultSet rs = null;
//        try {
//            selectPS(selectQuery());
//            rs = getSelectPS.executeQuery();
//            list = parseResultSet(rs);
//            return list;
//        } catch (SQLException e) {
//            throw new DaoException("Error in implementation of getAll method", e);
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }


/** в SelectById */
//        ResultSet rs = null;
//        Student student = new Student();
//        try {
//            selectByIdPS(selectByIdQuery());
//            preparedStatementForSelectById(getSelectByIdPS, id);
//            rs = getSelectByIdPS.executeQuery();
//            if (rs.next()) {
//                student.setFirstName(rs.getString(2));
//                student.setSecondName(rs.getString(3));
//                student.setCourse(rs.getInt(4));
//                student.setId(rs.getInt(1));
//                return student;
//            } else {
//                System.out.println("Student not Found!");
//                return null;
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Error in getById method", e);
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }


/** В Delete */
//            forDeleteByIdPS(forDeleteQuery());   // Доп. метод для удаления студентов из таблицы marks
//            preparedStatementForSelectById(getForDeleteByIdPS, id);
//            getForDeleteByIdPS.executeUpdate();
//            deleteByIdPS(deleteQuery());
//            preparedStatementForSelectById(getDeleteByIdPS, id);


/** В update */
//            updatePS(updateQuery());
//            preparedStatementForUpdate(getUpdatePS, student);


/**В insert */
//            insertPS(insertQuery());
//            preparedStatementForInsert(getInsertPS, student);


/**Создание PS*/
//    private void selectPS(String sql) throws DaoException {
//        try {
//            if (getSelectPS == null) {
//                getSelectPS = connection.prepareStatement(sql);
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Error in PreparedStatement for getAll", e);
//        }
//    }
//
//    private void selectByIdPS(String sql) throws DaoException {
//        try {
//            if (getSelectByIdPS == null) {
//                getSelectByIdPS = connection.prepareStatement(sql);
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Error in PreparedStatement for getSelectByIdPS", e);
//        }
//    }

//    private void insertPS(String sql) throws DaoException {
//        try {
//            if (getInsertPS == null) {
//                getInsertPS = connection.prepareStatement(sql);
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Error in PreparedStatement for getSelectByIdPS", e);
//        }
//    }

//    private void updatePS(String sql) throws DaoException {
//        try {
//            if (getUpdatePS == null) {
//                getUpdatePS = connection.prepareStatement(sql);
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Error in PreparedStatement for getSelectByIdPS", e);
//        }
//    }
//
//    private void deleteByIdPS(String sql) throws DaoException {
//        try {
//            if (getDeleteByIdPS == null) {
//                getDeleteByIdPS = connection.prepareStatement(sql);
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Error in PreparedStatement for getSelectByIdPS", e);
//        }
//    }
//
//    private void forDeleteByIdPS(String sql) throws DaoException {
//        try {
//            if (getForDeleteByIdPS == null) {
//                getForDeleteByIdPS = connection.prepareStatement(sql);
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Error in PreparedStatement for getSelectByIdPS", e);
//        }
//    }


/**
 * Закрытие до
 * <p>
 * //    public void close(AutoCloseable resource) throws DaoException {
 * //        try {
 * //            if (resource != null) {
 * //                resource.close();
 * //                System.out.println(" is closed ");
 * //            }
 * //        } catch (Exception e) {
 * //            throw new DaoException(e);
 * //        }
 * //    }
 * //
 * //    public void close1(AutoCloseable resource) throws DaoException {
 * //        try {
 * //            if (resource != null) {
 * //                resource.close();
 * //                throw new DaoException("Error in " + resource + " method");
 * //            }
 * //        } catch (Exception e) {
 * //            System.err.println("Error in close method");
 * //            throw new DaoException(e);
 * //        }
 * //    }
 * //
 * //    public void close() throws DaoException {
 * //        close(getSelectPS);
 * //        close1(getSelectByIdPS);
 * //        close(getInsertPS);
 * //        close(getUpdatePS);
 * //        close(getDeleteByIdPS);
 * //        close(getForDeleteByIdPS);
 * //        close(connection);
 * //    }
 */


//    public void close() throws DaoException {
//        for (PreparedStatement prp : preparedStatementList){
//            try {
//                close1(prp);
//            }catch (DaoException e){
//                throw new DaoException(e);
//            }
//        }
//    }
//}


//    public static void closeResource (Statement resource) throws DaoException {
//        if (resource != null) {
//            try {
//                resource.close();
//                System.out.println(resource + " is closed");
//            } catch (Exception e) {
//                throw new DaoException("Error in " + resource + " method", e);
//            }
//        }
//    }
//
//    public static void closeResource (Connection connection) throws DaoException {
//        if (connection != null) {
//            try {
//                connection.close();
//                System.out.println(connection + " is closed");
//            } catch (Exception e) {
//                throw new DaoException("Error in " + connection + " method", e);
//            }
//        }
//    }
//
//    public static void closeResource(PreparedStatement... resources) {
//        for (PreparedStatement resource : resources) {
//            try {
//                closeResource(resources);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

