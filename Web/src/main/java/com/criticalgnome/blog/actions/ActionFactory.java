package com.criticalgnome.blog.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
public class ActionFactory {

    /**
     * Return current Action based on 'action' parameter
     * @param request HttpServletRequest
     * @param responce HttpServletResponse
     * @return action
     */
    public static Action getAction(HttpServletRequest request, HttpServletResponse responce) {
        Action action;
        ActionsTable entry = ActionsTable.valueOf(request.getParameter("action").toUpperCase());
        action = entry.getCommand();
        return action;
    }
}
