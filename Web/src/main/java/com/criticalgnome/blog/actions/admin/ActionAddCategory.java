package com.criticalgnome.blog.actions.admin;

import com.criticalgnome.blog.actions.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project Blog
 * Created on 08.04.2017.
 *
 * @author CriticalGnome
 */
public class ActionAddCategory implements Action {
    /**
     * Return target page
     *
     * @param request  http request
     * @param response http response
     * @return action
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
