package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.CategoryDTO;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import com.criticalgnome.blog.services.IRoleService;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.utils.CategoriesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Project Blog
 * Created on 26.04.2017.
 *
 * @author CriticalGnome
 */
@Controller
public class AdminController {

    private final IUserService userService;
    private final IRoleService roleService;
    private final ICategoryService categoryService;

    @Autowired
    public AdminController(IUserService userService, IRoleService roleService, ICategoryService categoryService) {
        this.userService = userService;
        this.roleService = roleService;
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @GetMapping(value = "/admin")
    public ModelAndView showAdminPanel(ModelAndView model) {
        try {
            List<User> users = userService.getAll();
            List<Role> roles = roleService.getAll();
            List<Category> categories = categoryService.getAll();
            List<CategoryDTO> categoryDTOs = CategoriesList.get(categories);
            model.setViewName("admin");
            model.addObject("users", users);
            model.addObject("roles", roles);
            model.addObject("categories", categories);
            model.addObject("categoryDTOs", categoryDTOs);
            return model;
        } catch (ServiceException e) {
            return new ModelAndView("error", "message", e.getMessage());
        }
    }

}
