package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRecordService;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.utils.Alert;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@Log4j2
public class MainController {

    private final IRecordService recordService;
    private final IUserService userService;

    @Autowired
    public MainController(IRecordService recordService, IUserService userService) {
        this.recordService = recordService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public ModelAndView showHomePage(ModelAndView model) {
        try {
            composeMainPage(model);
        } catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
        }
        return model;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        return new ModelAndView("login","user", new User());
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView doLogin(ModelAndView model, @ModelAttribute User user) {
        try {
            user = userService.getByEmailAndPassword(user.getEmail(), user.getPassword());
            if (user != null) {
                composeMainPage(model);
                model.addObject("alert", new Alert("alert-success", "Success sign in"));
            } else {
                model.setViewName("login");
                model.addObject("alert", new Alert("alert-danger", "Incorrect login/password"));
            }
        } catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
        }
        return model;
    }

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String showAboutPage() {
        return "about";
    }

    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String composeNewRecord() {
        return "write";
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String showAdminPanel() {
        return "admin";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register","user", new User());
    }

    private void composeMainPage(ModelAndView model) throws ServiceException {
        List<Record> records;
        int pageNumber = SiteConstants.DEFAULT_PAGE;
        int recordsPerPage = SiteConstants.RECORDS_PER_PAGE;
        records = recordService.getRecordsByPage(pageNumber, recordsPerPage);
        int recordsCount = recordService.getRecordsCount();
        model.setViewName("main");
        model.addObject("records", records);
        model.addObject("pageNumber", pageNumber);
        model.addObject("recordsPerPage", recordsPerPage);
        model.addObject("recordsCount", recordsCount);
    }

}
