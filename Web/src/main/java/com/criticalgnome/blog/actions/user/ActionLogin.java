package com.criticalgnome.blog.actions.user;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.services.impl.UserServiceImpl;
import com.criticalgnome.blog.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
@Component
public class ActionLogin implements Action {

    @Autowired
    IUserService userService;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle((String) session.getAttribute("locale"));
        String page;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = userService.getByEmailAndPassword(email, password);
            if (user != null) {
                session.setAttribute("user", user);
                Alert alert = new Alert("alert-success", bundle.getString("alert.loggedin"));
                session.setAttribute("alert", alert);
                page = "index.jsp";
            } else {
                Alert alert =  new Alert("alert-danger", bundle.getString("alert.wronglogin"));
                session.setAttribute("alert", alert);
                page = "login.jsp";
            }
        } catch (DaoException | ServiceException e) {
            session.setAttribute("message", e.getMessage());
            page = SiteConstants.ERROR_PAGE;
        }
        return page;
    }
}
