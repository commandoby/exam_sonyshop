package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getAllByCategory(Category category) throws RepositoryException;

    Product getProductByName(String productName) throws RepositoryException;

    List<Product> getAllByCategoryAndQuantityNotLike(Category category, Integer quantity) throws RepositoryException;
}
