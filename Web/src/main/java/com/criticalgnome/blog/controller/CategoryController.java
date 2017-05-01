package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.CategoryDTO;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import com.criticalgnome.blog.utils.CategoriesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Project Blog
 * Created on 29.04.2017.
 *
 * @author CriticalGnome
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_EDITOR')")
    @GetMapping(value = "/add")
    public ModelAndView addCategory(ModelAndView model) {
        try {
            List<Category> categories = categoryService.getAll();
            List<CategoryDTO> categoryDTOs = CategoriesList.get(categories);
            model.setViewName("category");
            model.addObject("categoryDTO", new CategoryDTO());
            model.addObject("categoryDTOs", categoryDTOs);
            return model;
        } catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
            return model;
        }
    }

    @GetMapping(value = "/{id}")
    public String showCategory(@PathVariable Long id, HttpSession session, Model model) {
        session.removeAttribute("categoryScope");
        if (!id.equals(0L)) {
            try {
                Category category = categoryService.getById(id);
                session.setAttribute("categoryScope", category);
            } catch (ServiceException e) {
                model.addAttribute("message", e.getMessage());
                return "error";
            }
        }
        return "redirect:/";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_EDITOR')")
    @GetMapping(value = "/edit/{id}")
    public ModelAndView editCategory(@PathVariable Long id, ModelAndView model) {
        try {
            Category category = categoryService.getById(id);
            CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
            List<Category> categories = categoryService.getAll();
            List<CategoryDTO> categoryDTOs = CategoriesList.get(categories);
            model.setViewName("category");
            model.addObject("categoryDTO", categoryDTO);
            model.addObject("categoryDTOs", categoryDTOs);
            if (category.getCategory() != null) {
                model.addObject("parentCategoryId", category.getCategory().getId());
            } else {
                model.addObject("parentCategoryId", 0);
            }
            return model;
        } catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
            return model;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_EDITOR')")
    @GetMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        try {
            categoryService.remove(id);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_EDITOR')")
    @PostMapping(value = "")
    public String saveCategory(@ModelAttribute CategoryDTO categoryDTO, @RequestParam(name = "category", required = false) Long categoryId, Model model) {
        try {
            Category parentCategory;
            if (categoryId.equals(0L)) {
                parentCategory = null;
            } else {
                parentCategory = categoryService.getById(categoryId);
            }
            Category category = new Category(categoryDTO.getId(), categoryDTO.getName(), parentCategory);
            if (categoryDTO.getId() == null) {
                categoryService.create(category);
            } else {
                categoryService.update(category);
            }
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "redirect:/admin";
    }

}
