package com.criticalgnome.blog.actions.content;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project Blog
 * Created on 03.04.2017.
 *
 * @author CriticalGnome
 */
public class ActionChangePagination {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        session.setAttribute("recordsPerPage", recordsPerPage);
        return request.getHeader("referer");
    }
}
