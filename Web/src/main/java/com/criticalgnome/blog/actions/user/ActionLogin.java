package com.criticalgnome.blog.actions.user;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.dao.exceptions.DAOException;
import com.criticalgnome.blog.dao.implement.UserDAO;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.services.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
public class ActionLogin implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = UserDAO.getInstance().getByEmailAndPassword(email, password);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
