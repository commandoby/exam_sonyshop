package com.commandoby.sonyShop.dao.impl;
import com.commandoby.sonyShop.dao.UserDao;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {

    //SQL-statements
    private static final String ADD_USER = "INSERT INTO users VALUES (NULL, ?, ?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String UPDATE_USER = "UPDATE users SET balance = ? WHERE email = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    //Error causes fields
    private static final String ERROR_IN_CREATE_USER = "Error while adding user to database";
    private static final String ERROR_IN_READ_USER_BY_ID = "Error while getting user by id";
    private static final String ERROR_IN_READ_USER_BY_EMAIL = "Error while getting user by email";
    private static final String ERROR_IN_UPDATE_USER = "Error while trying to update user in database";
    private static final String ERROR_IN_DELETE_USER = "Error while deleting user from database";

    @Override
    public int create(User user) throws DAOException {
        int userId;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getDateOfBirth());
            ps.setInt(6, user.getBalance());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            userId = rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException  e) {
            throw new DAOException(ERROR_IN_CREATE_USER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return userId;
    }

    @Override
    public User read(int id) throws DAOException {
        User user = null;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("date_of_birth"),
                        rs.getInt("balance"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_USER_BY_ID, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return user;
    }

    @Override
    public void update(User user) throws DAOException {
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setInt(1, user.getBalance());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_UPDATE_USER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        Connection connection = databaseConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_DELETE_USER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }

    @Override
    public User getUserByEmail(String email) throws DAOException {
        User user = null;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("date_of_birth"),
                        rs.getInt("balance"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_USER_BY_EMAIL, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return user;
    }

}
