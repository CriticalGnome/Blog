package com.criticalgnome.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Project Blog
 * Created on 30.04.2017.
 *
 * @author CriticalGnome
 */
@Controller
public class ErrorController {

    private final MessageSource messageSource;

    @Autowired
    public ErrorController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/error")
    public ModelAndView renderErrorPage(HttpServletRequest request, Locale locale) {
        int number = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String message = "";
        switch (number) {
            case 400: {
                message = messageSource.getMessage("errors.400", null, locale);
                break;
            }
            case 401: {
                message = messageSource.getMessage("errors.401", null, locale);
                break;
            }
            case 403: {
                message = messageSource.getMessage("errors.403", null, locale);
                break;
            }
            case 404: {
                message = messageSource.getMessage("errors.404", null, locale);
                break;
            }
            case 500: {
                message = messageSource.getMessage("errors.500", null, locale);
                break;
            }
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("errors");
        model.addObject("number", number);
        model.addObject("message", message);
        return model;
    }
}
