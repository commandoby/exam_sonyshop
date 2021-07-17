package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.repository.ProductRepository;
import com.commandoby.sonyShop.repository.SearchProductsRepository;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.repository.domain.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

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
        return productRepository.findById(id).orElse(null);
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
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) throws ServiceException {
        return productRepository.getAllByCategory(category);
    }

    @Override
    public Product getProductByName(String name) throws ServiceException {
        return productRepository.getProductByName(name);
    }

    @Override
    public List<Product> getProductsByNameLike(String text) throws ServiceException {
        return productRepository.getAllByNameContains(text);
    }

    @Override
    public List<Product> getProductsByDescriptionLike(String text) throws ServiceException {
        return productRepository.getAllByDescriptionContains(text);
    }

    @Override
    public List<Product> getProductsByNotEmptyQuantity() throws ServiceException {
        return productRepository.getAllByQuantityIsNotContaining(0);
    }

    @Override
    public List<Product> getSearchProductsByParams(String search_value, String category_tag, String search_comparing,
                                                   Integer min_price, Integer max_price) throws ServiceException {
        return searchProductsRepository.searchProductsByParams(search_value, category_tag, search_comparing,
                min_price, max_price);
    }
}
