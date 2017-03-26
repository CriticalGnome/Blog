package com.criticalgnome.blog.actions.content;

import com.criticalgnome.blog.dao.implement.CategoryDAO;
import com.criticalgnome.blog.dao.implement.RecordDAO;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DAOException;

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
        Record record = null;
        List<Category> categoryList = new ArrayList<Category>();
        if (request.getParameter("id") == null) {
            return "index.jsp";
        }
        int recordId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return "index.jsp";
        }
        User user = (User) session.getAttribute("user");
        try {
            record = RecordDAO.getInstance().getById(recordId);
            categoryList = CategoryDAO.getInstance().getAll();
        } catch (DAOException e) {
            session.setAttribute("message", e.getMessage());
            return "error.jsp";
        }
        if (user.getRole().getId() > 2 && !user.getId().equals(record.getAuthor().getId())) {
            session.setAttribute("message", "You have no permission to edit this record");
            return "error.jsp";
        }
        request.setAttribute("record", record);
        request.setAttribute("categories", categoryList);
        request.getRequestDispatcher("edit.jsp").forward(request, response);

        return null;
    }
}
