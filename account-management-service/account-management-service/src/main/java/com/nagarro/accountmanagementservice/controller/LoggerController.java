package com.nagarro.accountmanagementservice.controller;

import com.nagarro.accountmanagementservice.entity.Logger;
import com.nagarro.accountmanagementservice.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {

    @Autowired
    private LoggerService loggerService;

    // addLog
    public void addLog(Logger logger) {
        loggerService.addLog(logger);
    }
//
//    // showLog
    @GetMapping("/account/{accountId}/logs")
    public Logger showLog(@PathVariable int accountId) {
        return loggerService.showLog(accountId);
    }
//
//    public void deleteLog(int accountId) {
//        loggerService.deleteLog(accountId);
//    }
}
