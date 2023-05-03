package com.nagarro.accountmanagementservice.repositories;


import com.nagarro.accountmanagementservice.entity.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<Logger, Integer> {

}
