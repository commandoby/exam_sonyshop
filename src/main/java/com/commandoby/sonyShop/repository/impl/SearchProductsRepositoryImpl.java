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
	public Page<Product> searchProductsByParams(Map<String, String> paramsStringMap, Map<String, Integer> paramsIntMap)
			throws RepositoryException {
		List<Product> products = new ArrayList<>();
		List<Predicate> predicates = new ArrayList<>();
		Map<String, Order> orderMap = new HashMap<>();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> root = criteriaQuery.from(Product.class);

		orderMap.put("Price+", criteriaBuilder.asc(root.get("price")));
		orderMap.put("Price-", criteriaBuilder.desc(root.get("price")));
		orderMap.put("Name+", criteriaBuilder.asc(root.get("name")));
		orderMap.put("Name-", criteriaBuilder.desc(root.get("name")));

		if (paramsStringMap.get(SEARCH_VALUE.getValue()) != null
				&& !paramsStringMap.get(SEARCH_VALUE.getValue()).equals("")) {
			predicates.add(criteriaBuilder.or(
					criteriaBuilder.like(root.get("name"), "%" + paramsStringMap.get(SEARCH_VALUE.getValue()) + "%"),
					criteriaBuilder.like(root.get("description"),
							"%" + paramsStringMap.get(SEARCH_VALUE.getValue()) + "%")));
		}

		if (paramsStringMap.get(CATEGORY_TAG.getValue()) != null
				&& !paramsStringMap.get(CATEGORY_TAG.getValue()).equals("")) {
			Join<Product, Category> categoryJoin = root.join("category");
			predicates
					.add(criteriaBuilder.equal(categoryJoin.get("tag"), paramsStringMap.get(CATEGORY_TAG.getValue())));
		}

		if (!paramsStringMap.get(IS_QUANTITY.getValue()).equals("on")) {
			predicates.add(criteriaBuilder.notEqual(root.get("quantity"), 0));
		}

		if (paramsIntMap.get(MIN_PRICE.getValue()) != null) {
			predicates.add(
					criteriaBuilder.greaterThanOrEqualTo(root.get("price"), paramsIntMap.get(MIN_PRICE.getValue())));
		}

		if (paramsIntMap.get(MAX_PRICE.getValue()) != null) {
			predicates
					.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), paramsIntMap.get(MAX_PRICE.getValue())));
		}

		if (paramsStringMap.get(SEARCH_COMPARING.getValue()) != null) {
			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
					.orderBy(orderMap.get(paramsStringMap.get(SEARCH_COMPARING.getValue())));

			if (entityManager.createQuery(criteriaQuery).getResultList().size() > 0) {
				products = entityManager.createQuery(criteriaQuery)
						.setFirstResult((paramsIntMap.get(PAGE_NUMBER.getValue()) - 1)
								* paramsIntMap.get(PAGE_ITEMS.getValue()))
						.setMaxResults(paramsIntMap.get(PAGE_ITEMS.getValue())).getResultList();
			}
		}

		return new PageImpl<Product>(products,
				PageRequest.of(paramsIntMap.get(PAGE_NUMBER.getValue()) - 1, paramsIntMap.get(PAGE_ITEMS.getValue())),
				entityManager.createQuery(criteriaQuery).getResultStream().count());
	}
}
