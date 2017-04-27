package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import com.criticalgnome.blog.services.IRecordService;
import com.criticalgnome.blog.services.ITagService;
import com.criticalgnome.blog.utils.CategoriesList;
import com.criticalgnome.blog.utils.CategoryLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project Blog
 * Created on 26.04.2017.
 *
 * @author CriticalGnome
 */
@Controller
@RequestMapping("/records")
public class RecordController {

    private final IRecordService recordService;
    private final ICategoryService categoryService;
    private final ITagService tagService;

    @Autowired
    public RecordController(IRecordService recordService, ICategoryService categoryService, ITagService tagService) {
        this.recordService = recordService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @GetMapping(value = "/write")
    public ModelAndView composeNewRecord(ModelAndView model) {
        try {
            List<Category> categories = categoryService.getAll();
            List<CategoryLine> categoryLines = new ArrayList<>();
            CategoriesList.getSubcategories(categoryLines, categories, null, "");
            model.setViewName("write");
            model.addObject("record", new Record());
            model.addObject("categoryLines", categoryLines);
        } catch (ServiceException e) {
            return new ModelAndView("error", "message", e.getMessage());
        }
        return model;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editRecord(ModelAndView model, @PathVariable Long id) {
        try {
            Record record = recordService.getById(id);
            List<Category> categories = categoryService.getAll();
            List<CategoryLine> categoryLines = new ArrayList<>();
            CategoriesList.getSubcategories(categoryLines, categories, null, "");
            model.setViewName("write");
            model.addObject("record", record);
            model.addObject("categoryLines", categoryLines);
        } catch (ServiceException e) {
            return new ModelAndView("error", "message", e.getMessage());
        }
        return model;
    }

    @PostMapping(value = "")
    public String save(@ModelAttribute Record record, @RequestBody Long categoryId, @RequestBody String tags, Model model) {
        try {
            Category category = categoryService.getById(categoryId);
            String[] tagArray = tags.split(",");
            Set<Tag> tagSet = new HashSet<>();
            for (String tagName : tagArray) {
                Tag tag = tagService.getOrCreateTagByName(tagName.trim());
                tagSet.add(tag);
            }
            record.setCategory(category);
            record.setTags(tagSet);
            recordService.create(record);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "main";
    }
}
