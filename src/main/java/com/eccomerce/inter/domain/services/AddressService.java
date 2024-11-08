package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.Address;
import com.eccomerce.inter.jpa.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    private Date creation;

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address add(Address address) {
        address.setCreationDate(new Date());
        creation = address.getCreationDate();
        address.setUpdateDate(new Date());
        return addressRepository.saveAndFlush(address);
    }

    public Address change(Address address) {
        address.setCreationDate(creation);
        address.setUpdateDate(new Date());
        return addressRepository.saveAndFlush(address);
    }

    public void del(Long id) {
        Address address = addressRepository.findById(id).get();
        addressRepository.delete(address);
    }
}
