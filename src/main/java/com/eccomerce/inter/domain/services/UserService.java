package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.User;
import com.eccomerce.inter.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private Date creation;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User add(User user) {
        user.setUpdateDate(new Date());
        user.setCreationDate(new Date());
        creation = user.getCreationDate();
        return userRepository.saveAndFlush(user);
    }

    public User change(User user) {
        user.setUpdateDate(new Date());
        user.setCreationDate(creation);
        return userRepository.saveAndFlush(user);
    }

    public void del(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }
}
