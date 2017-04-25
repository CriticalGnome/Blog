package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRecordService;
import com.criticalgnome.blog.services.IRoleService;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    private final IUserService userService;
    private final IRoleService roleService;

    @Autowired
    public MainController(IRecordService recordService, IUserService userService, IRoleService roleService) {
        this.recordService = recordService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
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

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        return new ModelAndView("login","user", new User());
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute User user, ModelAndView model) {
        String page;
        try {
            user = userService.getByEmailAndPassword(user.getEmail(), user.getPassword());
            if (user != null) {
                model.addObject("alert", new Alert("alert-success", "Success sign in"));
                page = "main";
            } else {
                model.addObject("alert", new Alert("alert-danger", "Incorrect login/password"));
                page = "login";
            }
        } catch (ServiceException e) {
            model.addObject("message", e.getMessage());
            page = "error";
        }
        return page;
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

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        String page;
        if (result.hasErrors()) {
            List<ObjectError> objectErrors = result.getAllErrors();
            model.addAttribute("emailClass", "has-success");
            model.addAttribute("passwordClass", "has-success");
            model.addAttribute("nickNameClass", "has-success");
            model.addAttribute("firstNameClass", "has-success");
            model.addAttribute("lastNameClass", "has-success");
            for (ObjectError error : objectErrors) {
                String errorString = error.toString();
                if (errorString.contains("email")) { model.addAttribute("emailClass", "has-error"); }
                if (errorString.contains("password")) { model.addAttribute("passwordClass", "has-error"); }
                if (errorString.contains("nickName")) { model.addAttribute("nickNameClass", "has-error"); }
                if (errorString.contains("firstName")) { model.addAttribute("firstNameClass", "has-error"); }
                if (errorString.contains("lastName")) { model.addAttribute("lastNameClass", "has-error"); }
            }
            page = "register";
        } else {
            try {
                user.setRole(roleService.getByName("User"));
                userService.create(user);
                model.addAttribute("alert", new Alert("alert-success", "Success sign on"));
                page = "main";
            } catch (ServiceException e) {
                model.addAttribute("alert", new Alert("alert-danger", "Email or Nickname already registered"));
                page = "register";
            }
        }
        return page;
    }

}
