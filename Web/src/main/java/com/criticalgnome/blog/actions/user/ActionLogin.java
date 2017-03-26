package com.criticalgnome.blog.actions.user;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.dao.implement.UserDAO;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.utils.Alert;
import com.criticalgnome.blog.utils.MD5;

import javax.servlet.ServletException;
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
public class ActionLogin implements Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = null;
        String email = request.getParameter("email");
        String password = MD5.md5Encode(request.getParameter("password"));
        try {
            User user = UserDAO.getInstance().getByEmailAndPassword(email, password);
            if (user != null) {
                session.setAttribute("user", user);
                Alert alert = new Alert("alert-success", "You are logged in");
                session.setAttribute("alert", alert);
                page = "index.jsp";
            } else {
                Alert alert =  new Alert("alert-danger", "Wrong login or password");
                session.setAttribute("alert", alert);
                page = "login.jsp";
            }
        } catch (DAOException e) {
            Alert alert = new Alert("alert-danger", e.getMessage());
            session.setAttribute("alert", alert);
            page = "error.jsp";
        }
        return page;
    }
}
