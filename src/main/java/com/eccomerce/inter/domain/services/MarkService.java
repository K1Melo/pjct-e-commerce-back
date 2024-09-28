package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.Address;
import com.eccomerce.inter.domain.entities.Mark;
import com.eccomerce.inter.jdbc.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MarkService {
    @Autowired
    private MarkRepository markRepository;
    private Date creation;

    public List<Mark> getAll() {
        return markRepository.findAll();
    }

    public Mark add(Mark mark) {
        mark.setUpdateDate(new Date());
        mark.setCreationDate(new Date());
        creation = mark.getCreationDate();
        return markRepository.saveAndFlush(mark);
    }

    public Mark change(Mark mark) {
        mark.setUpdateDate(new Date());
        mark.setCreationDate(creation);
        return markRepository.saveAndFlush(mark);
    }

    public void del(Long id) {
        Mark mark = markRepository.findById(id).get();
        markRepository.delete(mark);
    }
}
