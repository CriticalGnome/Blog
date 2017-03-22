package com.criticalgnome.blog.actions.user;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.dao.implement.UserDAO;
import com.criticalgnome.blog.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        String password = ActionRegister.md5Encode(request.getParameter("password"));
        try {
            User user = UserDAO.getInstance().getByEmailAndPassword(email, password);
            if (user != null) {
                session.setAttribute("user", user);
                session.setAttribute("message", "Login");
                page = "index.jsp";
            } else {
                session.setAttribute("message", "fail");
                page = "login.jsp";
            }
        } catch (DAOException e) {
            session.setAttribute("message", e.getMessage());
            page = "error.jsp";
        }
        return page;
    }
}
