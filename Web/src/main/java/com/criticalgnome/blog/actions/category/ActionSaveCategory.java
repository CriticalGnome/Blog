package com.criticalgnome.blog.actions.category;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import com.criticalgnome.blog.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project Blog
 * Created on 09.04.2017.
 *
 * @author CriticalGnome
 */
@Component
public class ActionSaveCategory implements Action {
    
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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id;
            if (request.getParameter("id").equals("")) {
                id = null;
            } else {
                id = Long.valueOf(request.getParameter("id"));
            }
            String name = request.getParameter("name");
            Long parentId = Long.valueOf(request.getParameter("parentCategory"));
            Category parent;
            if (parentId == 0) {
                parent = null;
            } else {
                parent = categoryService.getById(parentId);
            }
            Category category = new Category(id, name, parent);
            if (id == null) {
                categoryService.create(category);
            } else {
                categoryService.update(category);
            }
        } catch (ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", e.getMessage());
            return SiteConstants.ERROR_PAGE;
        }
        return "adminarea.jsp";
    }
}
