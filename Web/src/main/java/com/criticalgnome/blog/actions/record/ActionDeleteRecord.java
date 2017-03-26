package com.criticalgnome.blog.actions.record;

import com.criticalgnome.blog.dao.implement.RecordDAO;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.utils.Alert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project Blog
 * Created on 26.03.2017.
 *
 * @author CriticalGnome
 */
public class ActionDeleteRecord implements com.criticalgnome.blog.actions.Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole().getId() > 2) {
            session.setAttribute("message", "You have no permission to delete this record");
            return "error.jsp";
        }
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            RecordDAO.getInstance().remove(id);
        } catch (DAOException e) {
            session.setAttribute("message", "Error in delete record area");
            return "error.jsp";
        }
        Alert alert = new Alert("alert-info", "Record deleted");
        session.setAttribute("alert", alert);
        return "index.jsp";
    }
}
