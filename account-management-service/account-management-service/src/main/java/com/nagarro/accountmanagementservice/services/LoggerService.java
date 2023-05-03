package com.nagarro.accountmanagementservice.services;

import com.nagarro.accountmanagementservice.entity.Logger;
import com.nagarro.accountmanagementservice.repositories.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    @Autowired
    LoggerRepository loggerRepository;

    public void addLog(Logger logger) {
        loggerRepository.save(logger);
    }

    public Logger showLog(int accountId) {
        return loggerRepository.findById(accountId).orElse(null);
    }
//
//    public void deleteLog(int accountId) {
//        loggerRepository.deleteById(accountId);
//    }
}
