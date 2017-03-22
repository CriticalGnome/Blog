package com.criticalgnome.blog.actions.content;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.dao.implement.RecordDAO;
import com.criticalgnome.blog.entities.Record;
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
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
public class ActionMainpage implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Record> records = new ArrayList<Record>();
        String page = null;
        try {
            records = RecordDAO.getInstance().getAll();
            request.setAttribute("records", records);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (DAOException e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", e.getMessage());
            page = "error.jsp";
        }
        return page;
    }
}
