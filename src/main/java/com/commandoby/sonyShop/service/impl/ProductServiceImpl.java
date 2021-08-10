package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.repository.ProductRepository;
import com.commandoby.sonyShop.repository.SearchProductsRepository;
import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;
import static com.commandoby.sonyShop.enums.RequestParamEnum.PAGE_NUMBER;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger log = Logger.getLogger(getClass());
    private final ProductRepository productRepository;
    private final SearchProductsRepository searchProductsRepository;

    public ProductServiceImpl(ProductRepository productRepository, SearchProductsRepository searchProductsRepository) {
        this.productRepository = productRepository;
        this.searchProductsRepository = searchProductsRepository;
    }

    @Override
    public int create(Product product) throws ServiceException {
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public Product read(int id) throws ServiceException {
        return productRepository.findById(id).orElseThrow(() ->
                new ServiceException("Error retrieving a product from the database by ID: " + id + ".", new Exception())
        );
    }

    @Override
    public void update(Product product) throws ServiceException {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) throws ServiceException {
        productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProducts() throws ServiceException {
        Optional<List<Product>> products = Optional.ofNullable(productRepository.findAll());
        return products.orElseThrow(() ->
                new ServiceException("Error getting a list of all products.", new Exception()));
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) throws ServiceException {
        Optional<List<Product>> products = Optional.ofNullable(productRepository.getAllByCategory(category));
        return products.orElseThrow(() ->
                new ServiceException("Error retrieving the list of products for category: "
                        + category.getName() + ".", new Exception()));
    }

    @Override
    public Product getProductByName(String name) throws ServiceException {
        Optional<Product> product = Optional.ofNullable(productRepository.getProductByName(name));
        return product.orElseThrow(() ->
                new ServiceException("Error retrieving the product for name: "
                        + name + ".", new Exception()));
    }

    @Override
    public List<Product> getProductsByCategoryAndQuantityNotNull(Category category) throws ServiceException {
        Optional<List<Product>> products = Optional.ofNullable(
                productRepository.getAllByCategoryAndQuantityNotLike(category, 0));
        return products.orElseThrow(() ->
                new ServiceException("Error retrieving the list of products for category: "
                        + category.getName() + ".", new Exception()));
    }

    @Override
    public List<Product> getSearchProductsByParams(Map<String, String> paramsStringMap,
                                                   Map<String, Integer> paramsIntegerMap) throws ServiceException {
        return searchProductsRepository.searchProductsByParams(paramsStringMap, paramsIntegerMap);
    }


    @Override
    public void prePagination(ModelMap modelMap, List<Product> products,
                              Integer pageItems, Integer pageNumber) throws ServiceException {
        if (!pageItems.equals(0) && !products.isEmpty()) {
            List<Product> productPageList = pagination(modelMap, products, pageItems, pageNumber);
            modelMap.addAttribute(PRODUCT_LIST.getValue(), productPageList);
        } else {
            modelMap.addAttribute(PRODUCT_LIST.getValue(), products);
            modelMap.addAttribute(PAGE_NUMBER.getValue(), "1");
        }
    }

    private List<Product> pagination(ModelMap modelMap, List<Product> products,
                                     Integer pageItems, Integer pageNumber) {
        List<Product> productPageList = null;
        try {
            int pages = (int) Math.ceil(products.size() / pageItems.doubleValue());
            if (pageNumber > pages) pageNumber = pages;
            productPageList = getProductsPage(products, pageItems, pageNumber);
            modelMap.addAttribute(PAGE_MAX.getValue(), pages);
            modelMap.addAttribute(PAGE_NUMBER.getValue(), pageNumber);
        } catch (NumberFormatException e) {
            log.error(e);
        }
        return productPageList;
    }

    private List<Product> getProductsPage(List<Product> products, int pageItems, int pageNumber) {
        List<Product> newProductList = new ArrayList<>();
        products.stream()
                .skip((long) pageItems * (pageNumber - 1))
                .limit(pageItems)
                .forEach(newProductList::add);
        return newProductList;
    }

    @Override
    public Map<String, String> defaultParamsStringMap(
            Map<String, String> paramsStringMap) throws ServiceException {
        paramsStringMap.putIfAbsent(SEARCH_VALUE.getValue(), "");
        paramsStringMap.putIfAbsent(CATEGORY_TAG.getValue(), "");
        paramsStringMap.putIfAbsent(IS_QUANTITY.getValue(), "");
        if (paramsStringMap.get(SEARCH_COMPARING.getValue()) == null
                || paramsStringMap.get(SEARCH_COMPARING.getValue()).equals("")) {
            paramsStringMap.put(SEARCH_COMPARING.getValue(), "Price+");
        }
        return paramsStringMap;
    }

    @Override
    public Map<String, Integer> defaultParamsIntegerMap(
            Map<String, Integer> paramsIntegerMap) throws ServiceException {
        paramsIntegerMap.putIfAbsent(PAGE_ITEMS.getValue(), 0);
        paramsIntegerMap.putIfAbsent(PAGE_NUMBER.getValue(), 1);
        if (paramsIntegerMap.get(MIN_PRICE.getValue()) != null
                && paramsIntegerMap.get(MAX_PRICE.getValue()) != null
                && paramsIntegerMap.get(MAX_PRICE.getValue()) < paramsIntegerMap.get(MIN_PRICE.getValue())) {
            paramsIntegerMap.put(MAX_PRICE.getValue(), paramsIntegerMap.get(MIN_PRICE.getValue()));
        }
        return paramsIntegerMap;
    }
}
