package com.nagarro.customermanagementservice.services;

import com.nagarro.customermanagementservice.entity.Customers;
import com.nagarro.customermanagementservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    //getting all customer record by using the method findAll() of JpaRepository
    public List<Customers> getAllCustomers() {
        List<Customers> customers = new ArrayList<Customers>();
        customerRepository.findAll().forEach(customers1 -> customers.add(customers1));
        return customers;
    }

    //getting a specific record by using the method findById() of JpaRepository
    public Customers getCustomerById(int id) {
        return customerRepository.findById(id).get();
    }

    //saving a specific record by using the method save() of JpaRepository
    public void saveOrUpdate(Customers customers) {
        customerRepository.save(customers);
    }

    //deleting a specific record by using the method deleteById() of JpaRepository
    public void delete(int id){
        customerRepository.deleteById(id);
    }

    //updating a record
    public void update(Customers customers, int id ) {
        customerRepository.save(customers);
    }
}
