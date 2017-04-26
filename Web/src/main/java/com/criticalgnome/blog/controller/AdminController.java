package com.criticalgnome.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project Blog
 * Created on 26.04.2017.
 *
 * @author CriticalGnome
 */
@Controller
public class AdminController {

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String showAdminPanel() {
        return "admin";
    }

}
