package com.criticalgnome.blog.actions.user;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.utils.Alert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project Blog
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
public class ActionLogout implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        Alert alert = new Alert("alert-info", "Now you logger out");
        request.setAttribute("alert", alert);
        return "index.jsp";
    }
}
