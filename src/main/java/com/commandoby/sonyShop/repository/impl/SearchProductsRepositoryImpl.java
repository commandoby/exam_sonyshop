package com.commandoby.sonyShop.repository.impl;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.repository.SearchProductsRepository;
import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    public Page<Product> searchProductsByParams(Map<String, String> psm,
                                                Map<String, Integer> pim) throws RepositoryException {
        List<Product> products = null;
        List<Predicate> predicates = new ArrayList<>();
        Map<String, Order> orderMap = new HashMap<>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        orderMap.put("Price+", criteriaBuilder.asc(root.get("price")));
        orderMap.put("Price-", criteriaBuilder.desc(root.get("price")));
        orderMap.put("Name+", criteriaBuilder.asc(root.get("name")));
        orderMap.put("Name-", criteriaBuilder.desc(root.get("name")));

        if (psm.get(SEARCH_VALUE.getValue()) != null
                && !psm.get(SEARCH_VALUE.getValue()).equals("")) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), "%" + psm.get(SEARCH_VALUE.getValue()) + "%"),
                    criteriaBuilder.like(root.get("description"), "%" + psm.get(SEARCH_VALUE.getValue()) + "%")));
        }

        if (psm.get(CATEGORY_TAG.getValue()) != null
                && !psm.get(CATEGORY_TAG.getValue()).equals("")) {
            Join<Product, Category> categoryJoin = root.join("category");
            predicates.add(criteriaBuilder.equal(categoryJoin.get("tag"), psm.get(CATEGORY_TAG.getValue())));
        }

        if (!psm.get(IS_QUANTITY.getValue()).equals("on")) {
            predicates.add(criteriaBuilder.notEqual(root.get("quantity"), 0));
        }

        if (pim.get(MIN_PRICE.getValue()) != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), pim.get(MIN_PRICE.getValue())));
        }

        if (pim.get(MAX_PRICE.getValue()) != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), pim.get(MAX_PRICE.getValue())));
        }

        if (psm.get(SEARCH_COMPARING.getValue()) != null) {
            criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}))
                    .orderBy(orderMap.get(psm.get(SEARCH_COMPARING.getValue())));
			products = entityManager.createQuery(criteriaQuery)
					.setFirstResult((pim.get(PAGE_NUMBER.getValue()) - 1) * pim.get(PAGE_ITEMS.getValue()))
					.setMaxResults(pim.get(PAGE_ITEMS.getValue()))
					.getResultList();
		}

        return new PageImpl<Product>(products, PageRequest.of(pim.get(PAGE_NUMBER.getValue()) - 1, pim.get(PAGE_ITEMS.getValue())), 0);
    }
}
