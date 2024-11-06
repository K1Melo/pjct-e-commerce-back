package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.Permission;
import com.eccomerce.inter.domain.entities.PermissionUser;
import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.jpa.repositories.PermissionRepository;
import com.eccomerce.inter.jpa.repositories.PermissionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermissionUserService {
    @Autowired
    private PermissionUserRepository permissionUserRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public void bindClientPermission(User user) {
        List<Permission> permissionList = permissionRepository.findByName("Cliente");
        if (!permissionList.isEmpty()) {
            PermissionUser permissionUser = new PermissionUser();
            permissionUser.setUser(user);
            permissionUser.setPermission(permissionList.get(0));
            permissionUser.setCreationDate(new Date());
            permissionUser.setUpdateDate(new Date());
            permissionUserRepository.saveAndFlush(permissionUser);
        }
    }
}
