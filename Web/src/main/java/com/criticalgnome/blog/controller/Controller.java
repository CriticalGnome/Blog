package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.actions.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        if (request.getParameter("action") == null) {
            page = "error.jsp?reason=Illegal Call";
        } else {
            Action action = ActionFactory.getAction(request, response);
            if (action != null) {
                page = action.execute(request, response);
            } else {
                page = "error.jsp?reason=Illegal Call";
            }
        }
        if (page != null) {
            response.sendRedirect(page);
        }
    }
}
