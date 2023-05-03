package com.nagarro.customermanagementservice.controller;

import com.nagarro.customermanagementservice.entity.Customers;
import com.nagarro.customermanagementservice.repositories.CustomerRepository;
import com.nagarro.customermanagementservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custom")
public class CustomerController {

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private CustomerService customerService;

    //creating post mapping that post the customer detail in the database
    //Add Customer or create customer
    @PostMapping(path="/customers/add")
    private int addCustomer(@RequestBody Customers customers) {
        customerService.saveOrUpdate(customers);
        return customers.getId();
    }

    //creating a get mapping that retrieves all the customer details from the database
    //Get All Customers
    @GetMapping(path="/customer")
    private List<Customers> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    //creating a get mapping that retrieves the detail of a specific customer
    //Get Single Customer Details
    @GetMapping(path="/customer/{id}")
    private Customers getCustomers(@PathVariable("id") int id){
        return customerService.getCustomerById(id);
    }

    //creating put mapping that updates the customer detail
    //Update Customer Details
    @PutMapping(path="/customers")
    private Customers update(@RequestBody Customers customers) {
        customerService.saveOrUpdate(customers);
        return customers;
    }

    //creating delete mapping that deletes a specified customer
    //Delete Customer
    @DeleteMapping(path="/customer/{id}")
    private void deleteCustomer(@PathVariable("id") int id) {
        customerService.delete(id);
    }

//    @RequestMapping(path="/id/{id}")
//    public ResponseEntity<List<Customers>> getById(@PathVariable Integer id) {
//
//        List<Customers> listCustomer = repo.findByAccountNumber(id);
//        return new ResponseEntity<>(listCustomer, HttpStatus.OK );
//    }

//    @PostMapping(path="/add")
//    public ResponseEntity<Customers> add(@RequestBody Customers newCustomer) {
//
//        Customers customer = repo.save(newCustomer);
//        return new ResponseEntity<>(customer, HttpStatus.OK );
//    }
}
