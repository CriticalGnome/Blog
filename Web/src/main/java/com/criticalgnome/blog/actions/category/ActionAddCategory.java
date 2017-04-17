package com.criticalgnome.blog.actions.category;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import com.criticalgnome.blog.services.impl.CategoryServiceImpl;
import com.criticalgnome.blog.utils.CategoryLine;
import com.criticalgnome.blog.utils.GetCategoriesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Blog
 * Created on 08.04.2017.
 *
 * @author CriticalGnome
 */
@Component
public class ActionAddCategory implements Action {

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
            List<Category> categories = categoryService.getAll();
            List<CategoryLine> categoryLines = new ArrayList<>();
            categoryLines = GetCategoriesList.getSubcategories(categoryLines, categories, null, "");
            request.setAttribute("categoryLines", categoryLines);
            request.getRequestDispatcher("category.jsp").forward(request, response);

        } catch (ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", e.getMessage());
            return SiteConstants.ERROR_PAGE;
        }
        return null;
    }
}
