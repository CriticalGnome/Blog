package com.criticalgnome.blog.actions.record;

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
 * Created on 26.03.2017.
 *
 * @author CriticalGnome
 */
@Component
public class ActionWriteRecord {

    @Autowired
    ICategoryService categoryService;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Category> categories;
        List<CategoryLine> categoryLines = new ArrayList<>();
        try {
            categories = categoryService.getAll();
            categoryLines = GetCategoriesList.getSubcategories(categoryLines, categories, null, "");
        } catch (ServiceException e) {
            session.setAttribute("message", e.getMessage());
            return SiteConstants.ERROR_PAGE;
        }
        request.setAttribute("categoryLines", categoryLines);
        request.getRequestDispatcher("record.jsp").forward(request, response);

        return null;
    }
}
