package com.criticalgnome.blog.actions.record;

import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
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
import java.util.ResourceBundle;

/**
 * Project Blog
 * Created on 26.03.2017.
 *
 * @author CriticalGnome
 */
public class ActionEditRecord implements com.criticalgnome.blog.actions.Action {
    /**
     * Return target page
     *
     * @param request  http request
     * @param response http response
     * @return fake page, make redirect
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Record record;
        List<Category> categories;
        List<CategoryLine> categoryLines = new ArrayList<>();
        if (request.getParameter("id") == null) {
            return "index.jsp";
        }
        Long recordId = Long.parseLong(request.getParameter("id"));
        HttpSession session = request.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle((String) session.getAttribute("locale"));
        if (session.getAttribute("user") == null) {
            return "index.jsp";
        }
        User user = (User) session.getAttribute("user");
        try {
            record = RecordServiceImpl.getInstance().getById(recordId);
            categories = CategoryServiceImpl.getInstance().getAll();
            categoryLines = GetCategoriesList.getSubcategories(categoryLines, categories, null, "");
        } catch (DaoException | ServiceException e) {
            session.setAttribute("message", e.getMessage());
            return SiteConstants.ERROR_PAGE;
        }
        if (!user.getRole().getName().equals("Administrator") && !user.getRole().getName().equals("Editor") && !user.getId().equals(record.getAuthor().getId())) {
            session.setAttribute("message", bundle.getString("error.in.edit"));
            return SiteConstants.ERROR_PAGE;
        }
        StringBuilder tagString = new StringBuilder();
        for (Tag tag : record.getTags()){
            tagString.append(",").append(tag.getName());
        }
        if (tagString.length() > 0) {
            tagString.deleteCharAt(0);
        }
        request.setAttribute("record", record);
        request.setAttribute("categoryLines", categoryLines);
        request.setAttribute("tagString", tagString.toString());
        request.getRequestDispatcher("record.jsp").forward(request, response);

        return null;
    }
}
