package com.java.portfolio.service;

import com.java.portfolio.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.portfolio.repository.CustomerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
@Autowired
CustomerRepo customerRepo;
    public String save(Customer customer)
    {
         customerRepo.save(customer);
         return "Saved new Customer";
    }
    public List<Customer> getCustomers()
    {
        List<Customer> customers = customerRepo.findAll();
        return  customers;

    }
    public Optional<Customer>  getCustomerById(Integer id)
    {
        Optional<Customer> customer = customerRepo.findById(id);
        return customer;
    }
    public Optional<Customer>  getCustomerByName(String name)
    {
        Optional<Customer> customer = customerRepo.findByName(name);
        return customer;
    }

}
