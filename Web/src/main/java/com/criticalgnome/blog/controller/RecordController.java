package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.entities.*;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import com.criticalgnome.blog.services.IRecordService;
import com.criticalgnome.blog.services.ITagService;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.utils.CategoriesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.*;

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
    private final IUserService userService;

    @Autowired
    public RecordController(IRecordService recordService, ICategoryService categoryService, ITagService tagService, IUserService userService) {
        this.recordService = recordService;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @GetMapping(value = "/add")
    public ModelAndView composeNewRecord(ModelAndView model) {
        try {
            List<Category> categories = categoryService.getAll();
            List<CategoryDTO> categoryDTOs = new ArrayList<>();
            CategoriesList.getSubcategories(categoryDTOs, categories, null, "");
            model.setViewName("record");
            RecordDTO recordDTO = new RecordDTO();
            recordDTO.setId(0L);
            recordDTO.setAuthorId(0L);
            model.addObject("recordDTO", recordDTO);
            model.addObject("categoryDTOs", categoryDTOs);
            model.addObject("pageHeader", "New record");
        } catch (ServiceException e) {
            return new ModelAndView("error", "message", e.getMessage());
        }
        return model;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editRecord(ModelAndView model, @PathVariable Long id) {
        try {
            Record record = recordService.getById(id);
            StringBuilder tagString = new StringBuilder();
            for (Tag tag : record.getTags()) {
                tagString.append(tag.getName()).append(", ");
            }
            RecordDTO recordDTO = new RecordDTO(record.getId(), record.getHeader(), record.getBody(), record.getCategory().getId(), record.getAuthor().getId(), tagString.toString());
            List<Category> categories = categoryService.getAll();
            List<CategoryDTO> categoryDTOs = new ArrayList<>();
            CategoriesList.getSubcategories(categoryDTOs, categories, null, "");
            model.setViewName("record");
            model.addObject("recordDTO", recordDTO);
            model.addObject("categoryDTOs", categoryDTOs);
            model.addObject("pageHeader", "Edit record");
        } catch (ServiceException e) {
            return new ModelAndView("error", "message", e.getMessage());
        }
        return model;
    }

    @PostMapping(value = "")
    public String save(@ModelAttribute RecordDTO recordDTO, Model model) {
        try {
            Category category = categoryService.getById(recordDTO.getCategoryId());
            String[] tagArray = recordDTO.getTags().split(",");
            Set<Tag> tagSet = new HashSet<>();
            for (String tagName : tagArray) {
                Tag tag = tagService.getOrCreateTagByName(tagName.trim());
                tagSet.add(tag);
            }
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            Timestamp timestamp = new Timestamp(now.getTime());
            User author = userService.getById(recordDTO.getAuthorId());
            Record record = new Record(recordDTO.getId(), recordDTO.getHeader(), recordDTO.getBody(), timestamp, timestamp, category, author, tagSet);
            if (recordDTO.getId().equals(0L)) {
                UserDTO userDTO = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                record.setAuthor(userService.getById(userDTO.getId()));
                recordService.create(record);
            } else {
                recordService.update(record);
            }
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteRecord(Model model, @PathVariable Long id) {
        try {
            recordService.remove(id);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "redirect:/";
    }

}
