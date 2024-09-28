package com.eccomerce.inter.domain.services;

import com.eccomerce.inter.domain.entities.Address;
import com.eccomerce.inter.jdbc.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address add(Address address) {
        address.setCreationDate(new Date());
        return addressRepository.saveAndFlush(address);
    }

    public Address change(Address address) {

        address.setUpdateDate(new Date());
        return addressRepository.saveAndFlush(address);
    }

    public void del(Long id) {
        Address address = addressRepository.findById(id).get();
        addressRepository.delete(address);
    }
}
