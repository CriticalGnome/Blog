package com.criticalgnome.blog.actions.admin;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.implement.CategoryService;

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
public class ActionDeleteCategory implements Action {
    /**
     * Return target page
     *
     * @param request  http request
     * @param response http response
     * @return action
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CategoryService.getInstance().remove(Long.valueOf(request.getParameter("id")));
        } catch (DaoException | ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", e.getMessage());
            return SiteConstants.ERROR_PAGE;
        }
        return request.getHeader("referer");
    }
}
