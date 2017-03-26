package com.criticalgnome.blog.filter;

import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.utils.Alert;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Project Blog
 * Created on 26.03.2017.
 *
 * @author CriticalGnome
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = "/adminarea.jsp")
public class SecurityFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle((String) session.getAttribute("locale"));
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole().getId() != 1) {
            session.setAttribute("message", bundle.getString("access.denied"));
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {}
    public void init(FilterConfig config) throws ServletException {}

}
