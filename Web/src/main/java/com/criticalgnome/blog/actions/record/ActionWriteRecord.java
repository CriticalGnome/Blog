package com.criticalgnome.blog.actions.record;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.implement.CategoryService;

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
public class ActionWriteRecord implements com.criticalgnome.blog.actions.Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Category> categoryList = new ArrayList<Category>();
        try {
            categoryList = CategoryService.getInstance().getAll();
        } catch (DaoException | ServiceException e) {
            session.setAttribute("message", e.getMessage());
            return "error.jsp";
        }
        request.setAttribute("categories", categoryList);
        request.getRequestDispatcher("write.jsp").forward(request, response);

        return null;
    }
}
