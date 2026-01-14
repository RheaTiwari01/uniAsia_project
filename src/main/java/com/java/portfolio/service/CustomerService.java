package com.java.portfolio.service;

import com.java.portfolio.entity.Customer;
import com.java.portfolio.repository.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepo customerRepo;

    public String save(Customer customer) {
        try {
            customerRepo.save(customer);
            return "Saved new Customer";
        } catch (Exception e) {
            throw new RuntimeException("Failed to save customer");

        }
    }

    public List<Customer> getCustomers() {
        try {
            List<Customer> customers = customerRepo.findAll();
            return customers;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch customers");
        }
    }

    public Optional<Customer> getCustomerById(Integer id) {


        try {
            Optional<Customer> customer = customerRepo.findById(id);

            return customer;
        } catch (Exception e) {
            logger.error("Error fetching customer with id: {}", id, e);
            return Optional.empty();
        }
    }

    public Optional<Customer> getCustomerByName(String name) {


        try {
            Optional<Customer> customer = customerRepo.findByName(name);

            return customer;
        } catch (Exception e) {
            logger.error("Error fetching customer with name: {}", name, e);
            return Optional.empty();
        }
    }
}
