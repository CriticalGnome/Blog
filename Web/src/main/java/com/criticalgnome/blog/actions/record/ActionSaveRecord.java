package com.criticalgnome.blog.actions.record;

import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.dao.IUserDao;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import com.criticalgnome.blog.services.IRecordService;
import com.criticalgnome.blog.services.ITagService;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.services.impl.CategoryServiceImpl;
import com.criticalgnome.blog.services.impl.RecordServiceImpl;
import com.criticalgnome.blog.services.impl.TagServiceImpl;
import com.criticalgnome.blog.services.impl.UserServiceImpl;
import com.criticalgnome.blog.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class ActionSaveRecord implements com.criticalgnome.blog.actions.Action {
    
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IRecordService recordService;
    @Autowired
    ITagService tagService;
    @Autowired
    IUserService userService;
    
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
            Long id;
            if (request.getParameter("id").equals("")) {
                id = null;
            } else {
                id = Long.parseLong(request.getParameter("id"));
            }
            String header = request.getParameter("header");
            String body = request.getParameter("body");
            String tagString = request.getParameter("tags");
            String[] tagArray = tagString.split(",");
            Set<Tag> tags = new HashSet<>();
            for (String tagName : tagArray) {
                Tag tag = tagService.getOrCreateTagByName(tagName.trim());
                tags.add(tag);
            }
            Category category = categoryService.getById(Long.parseLong(request.getParameter("categoryId")));
            User author;
            if (request.getParameter("author").equals("")) {
                author = (User) session.getAttribute("user");
            } else {
                author = userService.getById(Long.parseLong(request.getParameter("author")));
            }
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            Timestamp timestamp = new Timestamp(now.getTime());
            Record record = new Record(id, header, body, timestamp, timestamp, category, author, tags);
            Alert alert = null;
            if (id  == null) {
                recordService.create(record);
                alert = new Alert("alert-success", bundle.getString("alert.record.saved"));
            } else {
                recordService.update(record);
                alert = new Alert("alert-success", bundle.getString("alert.record.updated"));
            }
            session.setAttribute("alert", alert);
            page = "index.jsp";
        } catch (ServiceException e) {
            session.setAttribute("message", e.getMessage());
            page = SiteConstants.ERROR_PAGE;
        }
        return page;
    }
}
