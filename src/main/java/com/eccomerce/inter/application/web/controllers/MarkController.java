package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.entities.Address;
import com.eccomerce.inter.domain.entities.Mark;
import com.eccomerce.inter.domain.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mark")
public class MarkController {

    @Autowired
    MarkService markService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/")
    public List<Mark> getAll() {
        return markService.getAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/")
    public Mark add(@RequestBody Mark mark) {
        return markService.add(mark);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/")
    public Mark change(@RequestBody Mark mark) {
        return markService.change(mark);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> del(@PathVariable("id") Long id) {
        markService.del(id);
        return ResponseEntity.ok().build();
    }
}
