package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.entities.Category;
import com.eccomerce.inter.domain.entities.Mark;
import com.eccomerce.inter.domain.services.CategoryService;
import com.eccomerce.inter.domain.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping("/")
    public Category add(@RequestBody Category category) {
        return categoryService.add(category);
    }

    @PutMapping("/")
    public Category change(@RequestBody Category category) {
        return categoryService.change(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> del(@PathVariable("id") Long id) {
        categoryService.del(id);
        return ResponseEntity.ok().build();
    }
}
