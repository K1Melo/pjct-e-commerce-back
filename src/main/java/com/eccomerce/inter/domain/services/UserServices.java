package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.resources.dto.UserDTO;
import com.eccomerce.inter.resources.jdbc.repository.UserRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepositoryJdbc userRepository;

    public List<UserDTO> findAll() {
        List<User> listUser = userRepository.findAll();
        return listUser.stream().map(UserDTO::new).toList();
    }

    public User postUser(User user) {
        userRepository.save(user);
        return user;
    }
}
