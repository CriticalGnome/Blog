package com.criticalgnome.blog.actions.record;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.implement.CategoryService;
import com.criticalgnome.blog.services.implement.RecordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        List<Category> categoryList;
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
            record = RecordService.getInstance().getById(recordId);
            categoryList = CategoryService.getInstance().getAll();
        } catch (DaoException | ServiceException e) {
            session.setAttribute("message", e.getMessage());
            return "error.jsp";
        }
        if (user.getRole().getId() > 2 && !user.getId().equals(record.getAuthor().getId())) {
            session.setAttribute("message", bundle.getString("error.in.edit"));
            return "error.jsp";
        }
        StringBuilder tagString = new StringBuilder();
        for (Tag tag : record.getTags()){
            tagString.append(",").append(tag.getName());
        }
        if (tagString.length() > 0) {
            tagString.deleteCharAt(0);
        }
        request.setAttribute("record", record);
        request.setAttribute("categories", categoryList);
        request.setAttribute("tagString", tagString.toString());
        request.getRequestDispatcher("edit.jsp").forward(request, response);

        return null;
    }
}
