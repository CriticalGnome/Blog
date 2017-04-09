package com.criticalgnome.blog.filter;

import com.criticalgnome.blog.utils.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Project Blog
 * Created on 09.04.2017.
 *
 * @author CriticalGnome
 */
@WebFilter(filterName = "SessionClosingFilter", servletNames = "Controller")
public class SessionClosingFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {}
    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Session session = HibernateUtil.getInstance().getSession();
        chain.doFilter(request, response);
        HibernateUtil.getInstance().releaseSession(session);
    }

}
