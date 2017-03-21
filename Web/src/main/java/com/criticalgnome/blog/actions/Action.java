package com.criticalgnome.blog.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
public interface Action {
    /**
     * Return target page
     * @param request http request
     * @param response http response
     * @return action
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
