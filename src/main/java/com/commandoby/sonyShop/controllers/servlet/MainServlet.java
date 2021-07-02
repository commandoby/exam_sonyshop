package com.commandoby.sonyShop.controllers.servlet;

import com.commandoby.sonyShop.controllers.commands.BaseCommand;
import com.commandoby.sonyShop.controllers.commands.CommandFactory;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sonyshop")
public class MainServlet extends HttpServlet {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseCommand requestCommand = CommandFactory.defineCommand(req);
        log.info(req.getParameter("command"));
        try {
            String path = "pages/" + requestCommand.execute(req);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        } catch (CommandException e) {
            log.error(e);
            req.getRequestDispatcher("pages/" + PagesPathEnum.HOME_PAGE.getPath()).forward(req, resp);
        }
    }
}
