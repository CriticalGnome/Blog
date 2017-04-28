package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRoleService;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Project Blog
 * Created on 26.04.2017.
 *
 * @author CriticalGnome
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;
    private final IRoleService roleService;
    private final MessageSource messageSource;

    @Autowired
    public UserController(IUserService userService, IRoleService roleService, MessageSource messageSource) {
        this.userService = userService;
        this.roleService = roleService;
        this.messageSource = messageSource;
    }

    @GetMapping(value = "/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login","user", new User());
    }

    @PostMapping(value = "/login")
    public String doLogin(@ModelAttribute User user, Model model, Locale locale, HttpSession session) {
        String page;
        try {
            user = userService.getByEmailAndPassword(user.getEmail(), user.getPassword());
            if (user != null) {
                session.setAttribute("authorisedUser", user);
                //model.addAttribute("alert", new Alert("alert-success", messageSource.getMessage("alert.login.success", null, locale)));
                page = "redirect:/";
            } else {
                model.addAttribute("alert", new Alert("alert-danger", messageSource.getMessage("alert.login.denied", null, locale)));
                page = "login";
            }
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            page = "error";
        }
        return page;
    }

    @GetMapping(value = "/logout")
    public String doLogout(HttpSession session /*, Model model, Locale locale */) {
        session.removeAttribute("authorisedUser");
                //model.addAttribute("alert", new Alert("alert-warning", messageSource.getMessage("alert.logout", null, locale)));
        return "redirect:/";
    }

    @GetMapping(value = "/register")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register","user", new User());
    }

    @PostMapping(value = "/register")
    public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, Locale locale) {
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
                //model.addAttribute("alert", new Alert("alert-success", messageSource.getMessage("alert.register.success", null, locale)));
                page = "redirect:/";
            } catch (ServiceException e) {
                model.addAttribute("alert", new Alert("alert-danger", messageSource.getMessage("alert.register.denied", null, locale)));
                page = "register";
            }
        }
        return page;
    }

}
