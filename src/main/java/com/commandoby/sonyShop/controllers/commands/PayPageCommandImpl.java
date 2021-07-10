package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.impl.UserServiceImpl;
import com.commandoby.sonyShop.utills.DataSourceHolder;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class PayPageCommandImpl implements BaseCommand {
    private final EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    private final Logger log = Logger.getLogger(getClass().getName());
    private final UserService userService = new UserServiceImpl(entityManager);

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();
        Order order = getOrder(servletRequest);
        User user = (User) session.getAttribute(USER.getValue());
        int paySize = order.getProductList().size();
        int payPrice = order.getOrderPrice();

        servletRequest.setAttribute(BASKET_SIZE.getValue(), paySize);
        servletRequest.setAttribute(BASKET_PRICE.getValue(), payPrice);

        if (paySize != 0 && user != null) {
            try {
            order.setUser(user);
                user.setBalance(user.getBalance() - payPrice);
                user.addOrder(order);
                userService.update(user);
                session.setAttribute(USER.getValue(), user);
                log.info("Purchased " + paySize + " products.");
            } catch (ServiceException e) {
                log.error(e);
            }
        }

        return PagesPathEnum.PAY_PAGE.getPath();
    }

    private Order getOrder(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Order order = (Order) session.getAttribute(ORDER.getValue());
        order.setDate(LocalDate.now().toString());
        session.setAttribute(ORDER.getValue(), new Order());
        return order;
    }
}
