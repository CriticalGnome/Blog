package com.criticalgnome.blog.actions.content;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.implement.RecordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        List<Record> records;
        String page = null;
        int pageNumber = SiteConstants.PAGE;
        int pageCapacity = SiteConstants.RECORDS_PER_PAGE;
        String leftPage;
        String leftPageClass;
        String rightPage;
        String rightPageClass;
        if (request.getParameter("page") != null) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        try {
            records = RecordService.getInstance().getRecordsByPage(pageNumber, pageCapacity);
            int recordsCount = RecordService.getInstance().getRecordsCount();
            if (pageNumber > 1) {
                leftPage = "controller?action=mainpage&page=" + (pageNumber - 1);
                leftPageClass = "";
            } else {
                leftPage = "#";
                leftPageClass = " disabled";
            }
            if (recordsCount >= pageNumber * pageCapacity) {
                rightPage = "controller?action=mainpage&page=" + (pageNumber + 1);
                rightPageClass = "";
            } else {
                rightPage = "#";
                rightPageClass = " disabled";
            }
            request.setAttribute("records", records);
            request.setAttribute("leftPage", leftPage);
            request.setAttribute("leftPageClass", leftPageClass);
            request.setAttribute("rightPage", rightPage);
            request.setAttribute("rightPageClass", rightPageClass);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (DaoException | ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", e.getMessage());
            page = SiteConstants.ERROR_PAGE;
        }
        return page;
    }
}
