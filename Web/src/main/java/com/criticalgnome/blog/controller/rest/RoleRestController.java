package com.criticalgnome.blog.controller.rest;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRoleService;
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
@RequestMapping("/rest/roles")
public class RoleRestController {

    private final IRoleService roleService;

    @Autowired
    public RoleRestController(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Create new role
     * @param role object to create
     * @return HttpStatus
     */
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PostMapping(value = "")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        try {
            roleService.create(role);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get Role by id
     * @param id role's id
     * @return object
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id) {
        Role role;
        try {
            role = roleService.getById(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    /**
     * Update role by id
     * @param id   role's id
     * @param role updated object
     * @return HttpStatus
     */
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Role role) {
        try {
            role.setId(id);
            roleService.update(role);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Delete role by id
     * @param id role's id
     * @return HttpStatus
     */
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            roleService.remove(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get all roles
     * @return List of roles
     */
    @GetMapping(value = "")
    public ResponseEntity<List<Role>> getAll() {
        List<Role> roles;
        try {
            roles = roleService.getAll();
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}
