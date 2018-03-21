package com.java.lesson.lesson10.dao;

import com.java.lesson.lesson10.Main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by User on 15.02.2018.
 */
public abstract class AbstractMySQLDao<T> {

    private Map<String, PreparedStatement> preparedStatements = new HashMap<>();

    protected Connection connection;

    public AbstractMySQLDao() throws DaoException {
        this.connection = getConnection();

    }

    protected abstract String selectQuery();

    protected abstract String selectByIdQuery();

    protected abstract String insertQuery();

    protected abstract String updateQuery();

    protected abstract String deleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws DaoException;

    protected abstract void preparedStatementForSelectById(PreparedStatement pst, int id) throws SQLException;

    protected abstract void preparedStatementForInsert(PreparedStatement pst, T t) throws SQLException;

    protected abstract void preparedStatementForUpdate(PreparedStatement pst, T t) throws SQLException;

    public Connection getConnection() throws DaoException {
        Properties property = new Properties();
        try (InputStream in = Main.class.getResourceAsStream("config.properties")) {
            property.load(in);
            String URL = property.getProperty("URL");
            String USER = property.getProperty("USER");
            String PASSWORD = property.getProperty("PASSWORD");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (IOException | SQLException e) {
            throw new DaoException("Connection error in getConnection method", e);
        }
    }

    public PreparedStatement getPreparedStatement(String sql) throws DaoException {
        PreparedStatement preparedStatement = preparedStatements.get(sql);
        if (preparedStatement == null) {
            try {
                preparedStatement = getConnection().prepareStatement(sql);
                preparedStatements.put(sql, preparedStatement);
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return preparedStatement;
    }

    public void close() throws DaoException {
//        Map<PreparedStatement, Exception> map = new HashMap<>();
        List<SQLException> list = new ArrayList<>();
        for (PreparedStatement preparedStatement : preparedStatements.values()) {
            try {
                if (preparedStatement != null) {
//                    throw new SQLException();
                    preparedStatement.close();
//                    System.out.println("close");
                }
            } catch (SQLException e) {
//                map.put(preparedStatement, e);
                list.add(e);
//                System.out.println(" add in list");
            }
        }
        if (connection != null) {
            try {
                connection.close();
//                System.out.println(" CONN CLOS");
            } catch (SQLException e) {
                list.add(e);
            }
        }
        Exception ex = new Exception();
        if (!list.isEmpty()) {
            for (SQLException e : list) {
                ex.addSuppressed(e);
            }
            throw new DaoException("Error in close DAO", ex);
        }
    }
}
