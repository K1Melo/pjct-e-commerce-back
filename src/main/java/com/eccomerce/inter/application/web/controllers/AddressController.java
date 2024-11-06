package com.eccomerce.inter.application.web.controllers;

import com.eccomerce.inter.domain.entities.Address;
import com.eccomerce.inter.domain.services.AddressService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/")
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/")
    public Address add(@RequestBody Address address) {
        return addressService.add(address);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/")
    public Address change(@RequestBody Address address) {
        return addressService.change(address);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> del(@PathVariable("id") Long id) {
        addressService.del(id);
        return ResponseEntity.ok().build();
    }
}
