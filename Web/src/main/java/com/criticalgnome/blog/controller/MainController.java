package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Project Blog
 * Created on 24.04.2017.
 *
 * @author CriticalGnome
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    IRecordService recordService;

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public ModelAndView homePage(ModelAndView model) {
        List<Record> records;
        int pageNumber = SiteConstants.DEFAULT_PAGE;
        int recordsPerPage = SiteConstants.RECORDS_PER_PAGE;
        try {
            records = recordService.getRecordsByPage(pageNumber, recordsPerPage);
            int recordsCount = recordService.getRecordsCount();
            model.setViewName("main");
            model.addObject("records", records);
            model.addObject("pageNumber", pageNumber);
            model.addObject("recordsPerPage", recordsPerPage);
            model.addObject("recordsCount", recordsCount);
        } catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
        }
        return model;
    }
}
