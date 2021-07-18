package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.OrderDao;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    //SQL-statements
    private static final String ADD_ORDER = "INSERT INTO orders VALUES (NULL, ?, ?, ?)";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String GET_ALL_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    private static final String UPDATE_ORDER = "UPDATE orders SET price = ? WHERE id = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE id = ?";
    private static final String ADD_PRODUCT_BY_ORDER = "INSERT INTO orders_products VALUES (?, ?)";
    private static final String GET_ALL_PRODUCT_ID_BY_ORDER_ID = "SELECT * FROM orders_products WHERE order_id = ?";

    //Error causes fields
    private static final String ERROR_IN_CREATE_ORDER = "Error while adding order to database";
    private static final String ERROR_IN_READ_ORDER_BY_ID = "Error while getting order by id";
    private static final String ERROR_IN_READ_ALL_ORDERS_BY_USER_ID =
            "Error while getting all orders by user id";
    private static final String ERROR_IN_UPDATE_ORDER = "Error while trying to update order in database";
    private static final String ERROR_IN_DELETE_ORDER = "Error while deleting order from database";
    private static final String ERROR_IN_CREATE_PRODUCT_BY_ORDER =
            "Error while adding product by order";
    private static final String ERROR_IN_READ_ALL_PRODUCT_ID_BY_ORDER =
            "Error while getting all product id by order";

    @Deprecated
    @Override
    public int create(Order order) throws DAOException {
        int orderId = 0;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getOrderPrice());
            ps.setString(2, order.getDate());
            ps.setInt(3, 1);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) orderId = rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_CREATE_ORDER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return orderId;
    }

    @Override
    public Order read(int id) throws DAOException {
        Order order = null;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order(rs.getString("date"));
                order.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_ORDER_BY_ID, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return order;
    }

    @Deprecated
    @Override
    public void update(Order entity) throws DAOException {
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER)) {

        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_UPDATE_ORDER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_DELETE_ORDER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }

    @Override
    public int createOrderByUser(Order order, int userId) throws DAOException {
        int orderId = 0;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getOrderPrice());
            ps.setString(2, order.getDate());
            ps.setInt(3, userId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) orderId = rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_CREATE_ORDER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return orderId;
    }

    @Override
    public void addProductByOrder(int orderId, int productId) throws DAOException {
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(ADD_PRODUCT_BY_ORDER)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_CREATE_PRODUCT_BY_ORDER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }

    @Override
    public List<Order> readAllOrdersByUser(int userId) throws DAOException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDERS_BY_USER_ID)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order(rs.getString("date"));
                order.setId(rs.getInt("id"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_ALL_ORDERS_BY_USER_ID, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return orderList;
    }

    @Override
    public List<Integer> readAllProductsIdByOrder(int orderId) throws DAOException {
        List<Integer> productIdList = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PRODUCT_ID_BY_ORDER_ID)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productIdList.add(rs.getInt("product_id"));
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_ALL_PRODUCT_ID_BY_ORDER, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
        return productIdList;
    }
}
