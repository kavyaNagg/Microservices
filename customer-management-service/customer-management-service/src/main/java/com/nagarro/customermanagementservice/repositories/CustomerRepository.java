package com.nagarro.customermanagementservice.repositories;

import com.nagarro.customermanagementservice.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//repository that extends JpaRepository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {

    public List<Customers> findByAccountNumber(Integer id);
}
