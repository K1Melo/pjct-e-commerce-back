package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.entities.Category;
import com.eccomerce.inter.domain.entities.Permission;
import com.eccomerce.inter.domain.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/")
    public List<Permission> getAll() {
        return permissionService.getAll();
    }

    @PostMapping("/")
    public Permission add(@RequestBody Permission permission) {
        return permissionService.add(permission);
    }

    @PutMapping("/")
    public Permission change(@RequestBody Permission permission) {
        return permissionService.change(permission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> del(@PathVariable("id") Long id) {
        permissionService.del(id);
        return ResponseEntity.ok().build();
    }
}
