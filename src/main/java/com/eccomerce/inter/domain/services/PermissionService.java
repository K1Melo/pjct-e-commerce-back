package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.Category;
import com.eccomerce.inter.domain.entities.Permission;
import com.eccomerce.inter.jpa.repositories.CategoryRepository;
import com.eccomerce.inter.jpa.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    private Date creation;

    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }

    public Permission add(Permission permission) {
        permission.setUpdateDate(new Date());
        permission.setCreationDate(new Date());
        creation = permission.getCreationDate();
        return permissionRepository.saveAndFlush(permission);
    }

    public Permission change(Permission permission) {
        permission.setUpdateDate(new Date());
        permission.setCreationDate(creation);
        return permissionRepository.saveAndFlush(permission);
    }

    public void del(Long id) {
        Permission permission = permissionRepository.findById(id).get();
        permissionRepository.delete(permission);
    }
}
