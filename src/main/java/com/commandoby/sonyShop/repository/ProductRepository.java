package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.repository.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getAllByCategory(Category category) throws RepositoryException;

    Product getProductByName(String productName) throws RepositoryException;

    List<Product> getAllByNameContains(String text) throws RepositoryException;

    List<Product> getAllByDescriptionContains(String text) throws RepositoryException;

    List<Product> getAllByQuantityIsNotContaining(int quantity) throws RepositoryException;
}
