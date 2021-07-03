package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class SignInCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass());
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();
        session.setAttribute(EMAIL.getValue(), "");
        session.setAttribute(PASSWORD.getValue(), "");

        if (servletRequest.getParameter(EMAIL.getValue()) != null) {
            if (!secondPasswordCheck(servletRequest)) {
                servletRequest.setAttribute(INFO.getValue(), "Password mismatch");
                return PagesPathEnum.REGISTER_PAGE.getPath();
            }

            if (duplicateCheck(servletRequest)) {
                servletRequest.setAttribute(INFO.getValue(), "User exists");
                return PagesPathEnum.REGISTER_PAGE.getPath();
            }

            User user = User.newBuilder()
                    .withName(servletRequest.getParameter(NAME.getValue()))
                    .withSurname(servletRequest.getParameter(SURNAME.getValue()))
                    .withDateOfBirth(servletRequest.getParameter(DATE_OF_BIRTH.getValue()))
                    .withEmail(servletRequest.getParameter(EMAIL.getValue()))
                    .withPassword(servletRequest.getParameter(PASSWORD.getValue()))
                    .withBalance(100000).build();
            try {
                userService.create(user);
            } catch (ServiceException e) {
                log.warn(e);
            }
        }

        return PagesPathEnum.SIGN_IN_PAGE.getPath();
    }

    private boolean secondPasswordCheck(HttpServletRequest servletRequest) {
        String password = servletRequest.getParameter(PASSWORD.getValue());
        String secondPassword = servletRequest.getParameter(SECOND_PASSWORD.getValue());
        if (password.equals(secondPassword)) return true;
        return false;
    }

    private boolean duplicateCheck(HttpServletRequest servletRequest) {
        String email = servletRequest.getParameter(EMAIL.getValue());
        try {
            List<String> emails = userService.getAllUsersEmails();
            for (String s : emails) if (s.equals(email)) return true;
        } catch (ServiceException e) {
            log.warn(e);
        }
        return false;
    }
}
