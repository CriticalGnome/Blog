package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRecordService;
import com.criticalgnome.blog.services.IRoleService;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    private final IRecordService recordService;

    @Autowired
    public MainController(IRecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping(value = "")
    public ModelAndView showHomePage(ModelAndView model) {
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

    @GetMapping(value = "login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login","user", new User());
    }

    @GetMapping(value = "logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping(value = "about")
    public String showAboutPage() {
        return "about";
    }

    @RequestMapping(value = "denied", method = RequestMethod.GET)
    public String accesssDenied() {
        return "denied";

    }

}
