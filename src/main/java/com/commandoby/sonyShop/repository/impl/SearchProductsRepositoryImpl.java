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
    public List<Product> searchProductsByParams(String search_value, String category_tag, String search_comparing,
                                                Integer min_price, Integer max_price) throws RepositoryException {
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
        if (!orderMap.containsKey(search_comparing)) search_comparing = "Price+";

        predicates.add(builder.or(
                builder.like(root.get("name"), "%" + search_value + "%"),
                builder.like(root.get("description"), "%" + search_value + "%")));

        if (category_tag != null && !category_tag.equals("")) {
            Join<Product, Category> categoryJoin = root.join("category");
            predicates.add(builder.equal(categoryJoin.get("tag"), category_tag));
        }

        if (min_price != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("price"), min_price));
        }

        if (max_price != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("price"), max_price));
        }

        criteria.select(root).where(predicates.toArray(new Predicate[]{}))
        .orderBy(orderMap.get(search_comparing));
        products = entityManager.createQuery(criteria).getResultList();

        return products;
    }
}
