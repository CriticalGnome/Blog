package com.criticalgnome.blog.actions.content;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.impl.CategoryServiceImpl;
import com.criticalgnome.blog.services.impl.RecordServiceImpl;
import com.criticalgnome.blog.utils.CategoryLine;
import com.criticalgnome.blog.utils.GetCategoriesList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Blog
 * Created on 05.04.2017.
 *
 * @author CriticalGnome
 */
public class ActionAdminArea implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Category> categories = CategoryServiceImpl.getInstance().getAll();
            List<CategoryLine> categoryLines = new ArrayList<>();
            categoryLines = GetCategoriesList.getSubcategories(categoryLines, categories, null, "");
            List<Record> records = RecordServiceImpl.getInstance().getRecordsByPage(1,5);
            int recordsCount = RecordServiceImpl.getInstance().getRecordsCount();

            request.setAttribute("categoryLines", categoryLines);
            request.setAttribute("records", records);
            request.setAttribute("recordsCount", recordsCount);
            request.getRequestDispatcher("adminarea.jsp").forward(request, response);
        } catch (DaoException | ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", e.getMessage());
            return SiteConstants.ERROR_PAGE;
        }

        return null;
    }
}
