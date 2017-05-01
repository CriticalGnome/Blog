package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.constants.SiteConstants;
import com.criticalgnome.blog.entities.*;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import com.criticalgnome.blog.services.IRecordService;
import com.criticalgnome.blog.utils.CategoriesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private final ICategoryService categoryService;

    @Autowired
    public MainController(IRecordService recordService, ICategoryService categoryService) {
        this.recordService = recordService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = {"", "page/{page}"})
    public ModelAndView showHomePage(ModelAndView model, HttpSession session, @PathVariable(required = false) Integer page) {
        try {
            int pageNumber;
            if (page != null) {
                pageNumber = page;
            } else {
                pageNumber = SiteConstants.DEFAULT_PAGE;
            }
            int recordsPerPage = SiteConstants.RECORDS_PER_PAGE;
            Category categoryScope = (Category) session.getAttribute("categoryScope");
            User userScope = (User) session.getAttribute("userScope");
            Tag tagScope = (Tag) session.getAttribute("tagScope");
            List<Record> records = recordService.getRecordsByPage(pageNumber, recordsPerPage, categoryScope, userScope, tagScope);
            int recordsCount = recordService.getRecordsCount(); // TODO Сделать возврат с учётом сужения критериев
            List<Category> categories = categoryService.getAll();
            List<CategoryDTO> categoryDTOs = CategoriesList.get(categories);

            model.setViewName("main");
            model.addObject("pageNumber", pageNumber);
            model.addObject("recordsPerPage", recordsPerPage);
            model.addObject("records", records);
            model.addObject("recordsCount", recordsCount);
            model.addObject("categoryDTOs", categoryDTOs);
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
    public String accessDenied() {
        return "denied";

    }

}
