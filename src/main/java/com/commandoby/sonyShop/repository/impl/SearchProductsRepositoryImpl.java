package com.commandoby.sonyShop.repository.impl;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.repository.SearchProductsRepository;
import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@Repository
public class SearchProductsRepositoryImpl implements SearchProductsRepository {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<Product> searchProductsByParams(Map<String, String> paramsStringMap,
                                                Map<String, Integer> paramsIntegerMap) throws RepositoryException {
        List<Product> products = null;
        List<Predicate> predicates = new ArrayList<>();
        Map<String, Order> orderMap = new HashMap<>();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);

        orderMap.put("Price+", builder.asc(root.get("price")));
        orderMap.put("Price-", builder.desc(root.get("price")));
        orderMap.put("Name+", builder.asc(root.get("name")));
        orderMap.put("Name-", builder.desc(root.get("name")));

        if (paramsStringMap.get(SEARCH_VALUE.getValue()) != null
                && !paramsStringMap.get(SEARCH_VALUE.getValue()).equals("")) {
            predicates.add(builder.or(
                    builder.like(root.get("name"), "%" + paramsStringMap.get(SEARCH_VALUE.getValue()) + "%"),
                    builder.like(root.get("description"), "%" + paramsStringMap.get(SEARCH_VALUE.getValue()) + "%")));
        }

        if (paramsStringMap.get(CATEGORY_TAG.getValue()) != null
                && !paramsStringMap.get(CATEGORY_TAG.getValue()).equals("")) {
            Join<Product, Category> categoryJoin = root.join("category");
            predicates.add(builder.equal(categoryJoin.get("tag"), paramsStringMap.get(CATEGORY_TAG.getValue())));
        }

        if (!paramsStringMap.get(IS_QUANTITY.getValue()).equals("on")) {
            predicates.add(builder.notEqual(root.get("quantity"), 0));
        }

        if (paramsIntegerMap.get(MIN_PRICE.getValue()) != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("price"), paramsIntegerMap.get(MIN_PRICE.getValue())));
        }

        if (paramsIntegerMap.get(MAX_PRICE.getValue()) != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("price"), paramsIntegerMap.get(MAX_PRICE.getValue())));
        }

        if (paramsStringMap.get(SEARCH_COMPARING.getValue()) != null) {
            criteria.select(root).where(predicates.toArray(new Predicate[]{}))
                    .orderBy(orderMap.get(paramsStringMap.get(SEARCH_COMPARING.getValue())));
            products = entityManager.createQuery(criteria).getResultList();
        }

        return products;
    }
}
