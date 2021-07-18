package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.ProductDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    //SQL-statements
    private static final String ADD_PRODUCT = "INSERT INTO products VALUES (NULL, ?, ?, ?, ?, ?)";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private static final String GET_PRODUCT_BY_NAME = "SELECT * FROM products WHERE name = ?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String GET_ALL_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id = ?";
    private static final String UPDATE_PRODUCT = "UPDATE products SET price = ? WHERE name = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE id = ?";

    //Error causes fields
    private static final String ERROR_IN_CREATE_PRODUCT = "Error while adding product to database";
    private static final String ERROR_IN_READ_PRODUCT_BY_ID = "Error while getting product by id";
    private static final String ERROR_IN_READ_PRODUCT_BY_NAME = "Error while getting product by name";
    private static final String ERROR_IN_READ_ALL_PRODUCTS = "Error while getting all products";
    private static final String ERROR_IN_READ_ALL_PRODUCTS_BY_CATEGORY_ID =
            "Error while getting all products by category id";
    private static final String ERROR_IN_READ_CATEGORY_ID_BY_ID = "Error while getting category id by product id";
    private static final String ERROR_IN_UPDATE_PRODUCT = "Error while trying to update product in database";
    private static final String ERROR_IN_DELETE_PRODUCT = "Error while deleting product from database";

    @Override
    public int create(Product product) throws DAOException {
        int productId = 0;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getImageName());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getPrice());
            ps.setInt(5, product.getCategory().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) productId = rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_CREATE_PRODUCT, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return productId;
    }

    @Override
    public Product read(int id) throws DAOException {
        Product product = null;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product(rs.getString("name"),
                        rs.getString("image_name"),
                        rs.getString("description"),
                        null,
                        rs.getInt("price"));
                product.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_PRODUCT_BY_ID, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return product;
    }

    @Override
    public void update(Product product) throws DAOException {
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT)) {
            ps.setInt(1, product.getPrice());
            ps.setString(2, product.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_UPDATE_PRODUCT, e);
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
            throw new DAOException(ERROR_IN_DELETE_PRODUCT, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }

    @Override
    public List<Product> getAllProducts() throws DAOException {
        List<Product> productList = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getString("name"),
                        rs.getString("image_name"),
                        rs.getString("description"),
                        null,
                        rs.getInt("price"));
                product.setId(rs.getInt("id"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_ALL_PRODUCTS, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return productList;
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) throws DAOException {
        List<Product> productList = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PRODUCTS_BY_CATEGORY_ID)) {
            ps.setInt(1, category.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getString("name"),
                        rs.getString("image_name"),
                        rs.getString("description"),
                        null,
                        rs.getInt("price"));
                product.setId(rs.getInt("id"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_ALL_PRODUCTS_BY_CATEGORY_ID, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return productList;
    }

    @Override
    public Product getProductByName(String name) throws DAOException {
        Product product = null;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_NAME)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product(rs.getString("name"),
                        rs.getString("image_name"),
                        rs.getString("description"),
                        null,
                        rs.getInt("price"));
                product.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_PRODUCT_BY_NAME, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return product;
    }

    @Override
    public int getCategoryId(int productId) throws DAOException {
        int categoryId = 0;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) categoryId = rs.getInt("category_id");
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_CATEGORY_ID_BY_ID, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return categoryId;
    }
}
