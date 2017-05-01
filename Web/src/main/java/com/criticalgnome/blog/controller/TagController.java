package com.criticalgnome.blog.controller;

import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Project Blog
 * Created on 01.05.2017.
 *
 * @author CriticalGnome
 */
@Controller
@RequestMapping("/tags")
public class TagController {

    private final ITagService tagService;

    @Autowired
    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value = "/{id}")
    public String showRecordsByTag(@PathVariable Long id, HttpSession session, Model model) {
        session.removeAttribute("tagScope");
        if (!id.equals(0L)) {
            try {
                Tag tag = tagService.getById(id);
                session.setAttribute("tagScope", tag);
            } catch (ServiceException e) {
                model.addAttribute("message", e.getMessage());
                return "error";
            }
        }
        return "redirect:/";
    }

}
