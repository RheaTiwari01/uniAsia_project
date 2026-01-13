package com.java.portfolio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.java.portfolio.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.java.portfolio.service.CustomerService;

import java.util.*;

@RestController
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService custser;

    @GetMapping("/getall")
    public ResponseEntity<List<Customer>> getAll() {

        try {
            List<Customer> customers = custser.getCustomers();

            if (customers.isEmpty()) {
                log.warn("No customers found");
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(customers);

        } catch (Exception e) {
            log.error("Error while fetching customers", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Integer id) {
        try {
            Optional<Customer> cust = custser.getCustomerById(id);

            if (cust.isPresent()) {
                return ResponseEntity.ok(cust.get());
            } else {
                log.warn("Customer not with id {}", id);
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            log.error("Error  fetching customer by id {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getbyname")
    public ResponseEntity<Customer> getByName(@RequestParam String name) {
        try {
            Optional<Customer> cust = custser.getCustomerByName(name);

            if (cust.isPresent()) {
                return ResponseEntity.ok(cust.get());
            } else {
                log.warn(" no customer found with name {}", name);
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            log.error("Error fetching customer name {}", name, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
