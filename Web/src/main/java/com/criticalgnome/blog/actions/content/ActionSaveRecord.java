package com.criticalgnome.blog.actions.content;

import com.criticalgnome.blog.dao.implement.CategoryDAO;
import com.criticalgnome.blog.dao.implement.RecordDAO;
import com.criticalgnome.blog.dao.implement.UserDAO;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.utils.Alert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Project Blog
 * Created on 26.03.2017.
 *
 * @author CriticalGnome
 */
public class ActionSaveRecord implements com.criticalgnome.blog.actions.Action {
    /**
     * Return target page
     *
     * @param request  http request
     * @param response http response
     * @return exit page
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String header = request.getParameter("header");
            String body = request.getParameter("body");
            Category category = CategoryDAO.getInstance().getById(Integer.parseInt(request.getParameter("categoryId")));
            User author = UserDAO.getInstance().getById(Integer.parseInt(request.getParameter("author")));
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            Timestamp timestamp = new Timestamp(now.getTime());
            List<Tag> tags = new ArrayList<>();
            Record record = new Record(id, header, body, timestamp, category, author, tags);
            RecordDAO.getInstance().update(record);
            Alert alert = new Alert("alert-success", "Record updated");
            session.setAttribute("alert", alert);
            page = "index.jsp";
        } catch (DAOException e) {
            Alert alert = new Alert("alert-danger", e.getMessage());
            session.setAttribute("alert", alert);
            page = "error.jsp";
        }
        return page;
    }
}
