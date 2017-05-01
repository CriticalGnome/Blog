package com.criticalgnome.blog.controller.rest;

import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRecordService;
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
@RequestMapping("/rest/records")
public class RecordRestController {

    private final IRecordService recordService;

    @Autowired
    public RecordRestController(IRecordService recordService) {
        this.recordService = recordService;
    }

    /**
     * Create new record
     * @param record object to create
     * @return HttpStatus
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "")
    public ResponseEntity<Record> create(@RequestBody Record record) {
        try {
            recordService.create(record);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get Record by id
     * @param id record's id
     * @return object
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Record> getById(@PathVariable Long id) {
        Record record;
        try {
            record = recordService.getById(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (record == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    /**
     * Update record by id
     * @param id     record's id
     * @param record updated object
     * @return HttpStatus
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Record record) {
        try {
            record.setId(id);
            recordService.update(record);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Delete record by id
     * @param id record's id
     * @return HttpStatus
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_EDITOR','ROLE_MODERATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            recordService.remove(id);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get all records
     * @return List of records
     */
    @GetMapping(value = "")
    public ResponseEntity<List<Record>> getAll() {
        List<Record> records;
        try {
            records = recordService.getAll();
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

}
