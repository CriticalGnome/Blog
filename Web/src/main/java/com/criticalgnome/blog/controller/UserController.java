package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.entities.Role;
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

    @GetMapping(value = "/register")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("user","user", new User());
    }

    @PostMapping(value = "")
    public String doRegister(@Valid @ModelAttribute("user") User user, @RequestParam(required = false) Long roleId, BindingResult result, Model model, Locale locale) {
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
            page = "user";
        } else {
            try {
                if (user.getId() == null) {
                    user.setRole(roleService.getByName("USER"));
                    userService.create(user);
                    page = "redirect:/";
                } else {
                    user.setRole(roleService.getById(roleId));
                    userService.update(user);
                    page = "redirect:/admin";
                }
                //model.addAttribute("alert", new Alert("alert-success", messageSource.getMessage("alert.register.success", null, locale)));
            } catch (ServiceException e) {
                model.addAttribute("alert", new Alert("alert-danger", messageSource.getMessage("alert.register.denied", null, locale)));
                page = "user";
            }
        }
        return page;
    }

    @GetMapping(value = "/add")
    public ModelAndView editUser(ModelAndView model) {
        try {
            List<Role> roles = roleService.getAll();
            model.setViewName("user");
            model.addObject("user", new User());
            model.addObject("roles", roles);
        } catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
        }
        return model;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editUser(@PathVariable Long id, ModelAndView model) {
        try {
            User user = userService.getById(id);
            List<Role> roles = roleService.getAll();
            model.setViewName("user");
            model.addObject("user", user);
            model.addObject("roles", roles);
        } catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
        }
        return model;
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        try {
            userService.remove(id);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "redirect:/admin";
    }

}
