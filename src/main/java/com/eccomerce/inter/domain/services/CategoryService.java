package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.Category;
import com.eccomerce.inter.domain.entities.Mark;
import com.eccomerce.inter.jdbc.repositories.CategoryRepository;
import com.eccomerce.inter.jdbc.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    private Date creation;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category add(Category category) {
        category.setUpdateDate(new Date());
        category.setCreationDate(new Date());
        creation = category.getCreationDate();
        return categoryRepository.saveAndFlush(category);
    }

    public Category change(Category category) {
        category.setUpdateDate(new Date());
        category.setCreationDate(creation);
        return categoryRepository.saveAndFlush(category);
    }

    public void del(Long id) {
        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
    }
}
