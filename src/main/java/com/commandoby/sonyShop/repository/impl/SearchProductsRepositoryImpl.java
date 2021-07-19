package com.commandoby.sonyShop.repository.impl;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.repository.SearchProductsRepository;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.repository.domain.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class SearchProductsRepositoryImpl implements SearchProductsRepository {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<Product> searchProductsByParams(String searchValue, String categoryTag, String searchComparing,
                                                String isQuantity, Integer minPrice, Integer maxPrice) throws RepositoryException {
        List<Product> products;
        List<Predicate> predicates = new ArrayList<>();
        Map<String, Order> orderMap = new HashMap<>();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);

        orderMap.put("Price+", builder.asc(root.get("price")));
        orderMap.put("Price-", builder.desc(root.get("price")));
        orderMap.put("Name+", builder.asc(root.get("name")));
        orderMap.put("Name-", builder.desc(root.get("name")));
        if (!orderMap.containsKey(searchComparing)) searchComparing = "Price+";

        predicates.add(builder.or(
                builder.like(root.get("name"), "%" + searchValue + "%"),
                builder.like(root.get("description"), "%" + searchValue + "%")));

        if (categoryTag != null && !categoryTag.equals("")) {
            Join<Product, Category> categoryJoin = root.join("category");
            predicates.add(builder.equal(categoryJoin.get("tag"), categoryTag));
        }

        if (!isQuantity.equals("on")) {
            predicates.add(builder.notEqual(root.get("quantity"), 0));
        }

        if (minPrice != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        criteria.select(root).where(predicates.toArray(new Predicate[]{}))
        .orderBy(orderMap.get(searchComparing));
        products = entityManager.createQuery(criteria).getResultList();

        return products;
    }
}
