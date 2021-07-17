package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.repository.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT u FROM Category u ORDER BY u.rating")
    List<Category> gelAllCategorySortByRating() throws RepositoryException;

    Category findCategoryByTag(String categoryTag) throws RepositoryException;
}
