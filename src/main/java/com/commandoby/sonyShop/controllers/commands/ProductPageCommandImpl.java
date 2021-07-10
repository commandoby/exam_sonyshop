package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.impl.ProductServiceImpl;
import com.commandoby.sonyShop.utills.DataSourceHolder;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class ProductPageCommandImpl implements BaseCommand{
    private final EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    private final Logger log = Logger.getLogger(getClass());
    private final ProductService productService = new ProductServiceImpl(entityManager);

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String productName = servletRequest.getParameter(PRODUCT_NAME.getValue());
        try {
            Product product = productService.getProductByName(productName);
            servletRequest.setAttribute(PRODUCT.getValue(), product);
            if (servletRequest.getParameter(PRODUCT_NAME_OUT_OF_PRODUCT.getValue()) != null) {
                BasketPageCommandImpl.addProductToBasket(servletRequest, product);
            }
        } catch (NoFoundException e) {
            log.error(e);
        } catch (ServiceException e) {
            log.warn(e);
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.PRODUCT_PAGE.getPath();
    }
}
