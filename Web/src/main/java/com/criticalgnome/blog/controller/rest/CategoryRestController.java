package com.criticalgnome.blog.controller.rest;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project Blog
 * Created on 01.05.2017.
 *
 * @author CriticalGnome
 */
@RestController
@RequestMapping("/rest/users")
public class CategoryRestController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryRestController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Create new category
     * @param category object to create
     * @return HttpStatus
     */
    @PostMapping(value = "")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        try {
            categoryService.create(category);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get Category by id
     * @param id category's id
     * @return object
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category;
        try {
            category = categoryService.getById(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Update category by id
     * @param id       category's id
     * @param category updated object
     * @return HttpStatus
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Category category) {
        try {
            category.setId(id);
            categoryService.update(category);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Delete category by id
     * @param id category's id
     * @return HttpStatus
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            categoryService.remove(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get all categories
     * @return List of categories
     */
    @GetMapping(value = "")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories;
        try {
            categories = categoryService.getAll();
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
