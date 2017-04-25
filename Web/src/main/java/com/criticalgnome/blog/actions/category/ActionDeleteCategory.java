package com.criticalgnome.blog.actions.category;

import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project Blog
 * Created on 08.04.2017.
 *
 * @author CriticalGnome
 */
@Component
public class ActionDeleteCategory {

    @Autowired
    ICategoryService categoryService;

    /**
     * Return target page
     *
     * @param request  http request
     * @param response http response
     * @return action
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            categoryService.remove(Long.valueOf(request.getParameter("id")));
        } catch (ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", e.getMessage());
            return SiteConstants.ERROR_PAGE;
        }
        return request.getHeader("referer");
    }
}
