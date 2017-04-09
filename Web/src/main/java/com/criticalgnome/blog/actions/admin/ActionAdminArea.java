package com.criticalgnome.blog.actions.admin;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.implement.CategoryService;
import com.criticalgnome.blog.services.implement.RecordService;
import com.criticalgnome.blog.utils.CategoryLine;
import com.criticalgnome.blog.utils.GetCategoriesList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            List<Category> categories = CategoryService.getInstance().getAll();
            List<CategoryLine> categoryLines = new ArrayList<>();
            categoryLines = GetCategoriesList.getSubcategories(categoryLines, categories, null, "");
            List<Record> records = RecordService.getInstance().getRecordsByPage(1,5);
            int recordsCount = RecordService.getInstance().getRecordsCount();

            request.setAttribute("categoryLines", categoryLines);
            request.setAttribute("records", records);
            request.setAttribute("recordsCount", recordsCount);
            request.getRequestDispatcher("adminarea.jsp").forward(request, response);
        } catch (DaoException | ServiceException e) {
            e.printStackTrace();
        }

        return null;
    }
}
