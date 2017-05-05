package com.criticalgnome.blog.controller.rest;

import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project Blog
 * Created on 01.05.2017.
 *
 * @author CriticalGnome
 */
@RestController
@RequestMapping("/rest/tags")
public class TagRestController {

    private final ITagService tagService;

    @Autowired
    public TagRestController(ITagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Create new tag
     * @param tag object to create
     * @return HttpStatus
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "")
    public ResponseEntity<Tag> create(@RequestBody Tag tag) {
        try {
            tagService.create(tag);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get Tag by id
     * @param id tag's id
     * @return object
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Tag> getById(@PathVariable Long id) {
        Tag tag;
        try {
            tag = tagService.getById(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (tag == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    /**
     * Update tag by id
     * @param id  tag's id
     * @param tag updated object
     * @return HttpStatus
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_EDITOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Tag tag) {
        try {
            tag.setId(id);
            tagService.update(tag);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Delete tag by id
     * @param id tag's id
     * @return HttpStatus
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_EDITOR','ROLE_MODERATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            tagService.remove(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get all tags
     * @return List of tags
     */
    @GetMapping(value = "")
    public ResponseEntity<List<Tag>> getAll() {
        List<Tag> tags;
        try {
            tags = tagService.getAll();
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

}
