package com.criticalgnome.blog.actions.record;

import com.criticalgnome.blog.dao.implement.CategoryDAOold;
import com.criticalgnome.blog.dao.implement.RecordDAOold;
import com.criticalgnome.blog.dao.implement.TagDAOold;
import com.criticalgnome.blog.dao.implement.UserDAOold;
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
import java.util.*;

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
        ResourceBundle bundle = ResourceBundle.getBundle((String) session.getAttribute("locale"));
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String header = request.getParameter("header");
            String body = request.getParameter("body");
            String tagString = request.getParameter("tags");
            String[] tagArray = tagString.split(",");
            List<Tag> tags = new ArrayList<>();
            for (String tagName : tagArray) {
                Tag tag = TagDAOold.getInstance().getOrCreateTagByName(tagName.toUpperCase().trim());
                tags.add(tag);
            }
            Category category = CategoryDAOold.getInstance().getById(Integer.parseInt(request.getParameter("categoryId")));
            User author = UserDAOold.getInstance().getById(Integer.parseInt(request.getParameter("author")));
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            Timestamp timestamp = new Timestamp(now.getTime());
            Record record = new Record(id, header, body, timestamp, category, author, tags);
            Alert alert = null;
            if (request.getParameter("mode").equals("update")) {
                RecordDAOold.getInstance().update(record);
                alert = new Alert("alert-success", bundle.getString("alert.record.updated"));
            }
            if (request.getParameter("mode").equals("create")) {
                id = RecordDAOold.getInstance().getMaxId() + 1;
                record.setId(id);
                RecordDAOold.getInstance().create(record);
                alert = new Alert("alert-success", bundle.getString("alert.record.saved"));
            }
            session.setAttribute("alert", alert);
            page = "index.jsp";
        } catch (DAOException e) {
            session.setAttribute("message", e.getMessage());
            page = "error.jsp";
        }
        return page;
    }
}
