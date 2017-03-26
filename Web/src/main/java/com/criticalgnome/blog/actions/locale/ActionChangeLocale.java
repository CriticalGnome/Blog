package com.criticalgnome.blog.actions.locale;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.utils.Alert;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
public class ActionChangeLocale implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle((String) session.getAttribute("locale"));
        String locale = LocaleFactory.getLocale(request.getParameter("locale"));
        session.setAttribute("locale", locale);
        Cookie c = new Cookie("locale", locale);
        c.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(c);
        Alert alert = new Alert("alert-info", bundle.getString("locale.changed"));
        session.setAttribute("alert", alert);
        return request.getHeader("referer");
    }
}
