package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.actions.ActionFactory;
import com.criticalgnome.blog.constants.SiteConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
@WebServlet(name = "Controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;

        HttpSession session = request.getSession();
        if (request.getParameter("action") == null) {
            session.setAttribute("message", "Illegal action Call");
            page = SiteConstants.ERROR_PAGE;
        } else {
            Action action = ActionFactory.getAction(request);
            if (action != null) {
                page = action.execute(request, response);
            } else {
                session.setAttribute("message", "Illegal action Call");
                page = SiteConstants.ERROR_PAGE;
            }
        }
        if (page != null) {
            response.sendRedirect(page);
        }
    }
}
