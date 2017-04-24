package com.criticalgnome.blog.actions.content;

import com.criticalgnome.blog.actions.Action;
import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRecordService;
import com.criticalgnome.blog.services.impl.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
@Component
public class ActionMainpage implements Action {
    
    @Autowired
    IRecordService recordService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Record> records;
        String page = null;
        int pageNumber = SiteConstants.DEFAULT_PAGE;
        int recordsPerPage = SiteConstants.RECORDS_PER_PAGE;
        if (request.getParameter("page") != null) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        if (session.getAttribute("recordsPerPage") != null) {
            recordsPerPage = (int) session.getAttribute("recordsPerPage");
        }
        try {
            records = recordService.getRecordsByPage(pageNumber, recordsPerPage);
            int recordsCount = recordService.getRecordsCount();
            request.setAttribute("records", records);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("recordsCount", recordsCount);
            request.getRequestDispatcher("old-index.jsp").forward(request, response);
        } catch (ServiceException e) {
            session.setAttribute("message", e.getMessage());
            page = SiteConstants.ERROR_PAGE;
        }
        return page;
    }
}
