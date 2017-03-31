package com.criticalgnome.blog.actions.record;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.implement.CategoryService;
import com.criticalgnome.blog.services.implement.RecordService;
import com.criticalgnome.blog.services.implement.TagService;
import com.criticalgnome.blog.services.implement.UserService;
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
            Long id = Long.parseLong(request.getParameter("id"));
            String header = request.getParameter("header");
            String body = request.getParameter("body");
            String tagString = request.getParameter("tags");
            String[] tagArray = tagString.split(",");
            Set<Tag> tags = new HashSet<Tag>();
            for (String tagName : tagArray) {
                Tag tag = TagService.getInstance().getOrCreateTagByName(tagName.trim());
                tags.add(tag);
            }
            Category category = CategoryService.getInstance().getById(Long.parseLong(request.getParameter("categoryId")));
            User author = UserService.getInstance().getById(Long.parseLong(request.getParameter("author")));
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            Timestamp timestamp = new Timestamp(now.getTime());
            Record record = new Record(id, header, body, timestamp, timestamp, category, author, tags);
            Alert alert = null;
            if (request.getParameter("mode").equals("update")) {
                RecordService.getInstance().update(record);
                alert = new Alert("alert-success", bundle.getString("alert.record.updated"));
            }
            if (request.getParameter("mode").equals("create")) {
                record.setId(null);
                RecordService.getInstance().create(record);
                alert = new Alert("alert-success", bundle.getString("alert.record.saved"));
            }
            session.setAttribute("alert", alert);
            page = "index.jsp";
        } catch (DaoException | ServiceException e) {
            session.setAttribute("message", e.getMessage());
            page = "error.jsp";
        }
        return page;
    }
}
