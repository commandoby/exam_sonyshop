package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.CategoryDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    //SQL-statements
    private static final String ADD_CATEGORY = "INSERT INTO categories VALUES (NULL, ?, ?, ?, ?)";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id = ?";
    private static final String GET_ALL_CATEGORY = "SELECT * FROM categories";
    private static final String UPDATE_CATEGORY = "UPDATE categories SET rating = ? WHERE tag = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM categories WHERE id = ?";

    //Error causes fields
    private static final String ERROR_IN_CREATE_CATEGORY = "Error while adding category to database";
    private static final String ERROR_IN_READ_CATEGORY_BY_ID = "Error while getting category from database";
    private static final String ERROR_IN_READ_ALL_CATEGORY = "Error while getting categories from database";
    private static final String ERROR_IN_UPDATE_CATEGORY = "Error while trying to update category in database";
    private static final String ERROR_IN_DELETE_CATEGORY = "Error while deleting category from database";

    @Override
    public int create(Category category) throws DAOException {
        int categoryId;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(ADD_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getTag());
            ps.setString(3, category.getImageName());
            ps.setInt(4, category.getRating());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            categoryId = rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_CREATE_CATEGORY, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return categoryId;
    }

    @Override
    public Category read(int id) throws DAOException {
        Category category = null;
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_CATEGORY_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                category = new Category(rs.getString("name"),
                        rs.getString("tag"),
                        rs.getString("image_name"),
                        rs.getInt("rating"));
                category.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_CATEGORY_BY_ID, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return category;
    }

    @Override
    public void update(Category category) throws DAOException {
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CATEGORY)) {
            ps.setInt(1, category.getRating());
            ps.setString(2, category.getTag());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_UPDATE_CATEGORY, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORY)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_DELETE_CATEGORY, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }
    }

    @Override
    public List<Category> getAllCategories() throws DAOException {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_CATEGORY)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category(rs.getString("name"),
                        rs.getString("tag"),
                        rs.getString("image_name"),
                        rs.getInt("rating"));
                category.setId(rs.getInt("id"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_IN_READ_ALL_CATEGORY, e);
        } finally {
            databaseConnection.closeConnection(connection);
        }

        return categoryList;
    }
}
