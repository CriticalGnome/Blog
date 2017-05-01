package com.criticalgnome.blog.controller.rest;

import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IUserService;
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
public class UserRestController {

    private final IUserService userService;

    @Autowired
    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Create new user
     * @param user object to create
     * @return HttpStatus
     */
    @PostMapping(value = "")
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            userService.create(user);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get User by id
     * @param id user's id
     * @return object
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user;
        try {
            user = userService.getById(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Update user by id
     * @param id   user's id
     * @param user updated object
     * @return HttpStatus
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody User user) {
        try {
            user.setId(id);
            userService.update(user);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Delete user by id
     * @param id user's id
     * @return HttpStatus
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            userService.remove(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get all users
     * @return List of users
     */
    @GetMapping(value = "")
    public ResponseEntity<List<User>> getAll() {
        List<User> users;
        try {
            users = userService.getAll();
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
