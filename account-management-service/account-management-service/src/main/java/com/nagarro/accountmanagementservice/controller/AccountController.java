package com.nagarro.accountmanagementservice.controller;

import com.nagarro.accountmanagementservice.entity.Account;
import com.nagarro.accountmanagementservice.model.Customer;
import com.nagarro.accountmanagementservice.entity.Logger;
import com.nagarro.accountmanagementservice.model.RequiredResponse;
import com.nagarro.accountmanagementservice.repositories.AccountRepository;
import com.nagarro.accountmanagementservice.services.AccountService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoggerController loggerController;

    @Autowired
    private RestTemplate restTemplate;

    //Get All Accounts
    @GetMapping(path="/account")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping(path="/add")
    public ResponseEntity<Account> addCustomer(@RequestBody Account account) {

        Account accountAdded = accountRepository.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK );
    }

    //Fault Tolerance using Hystrix
    //Get Account Details
    @GetMapping("/id/{accountId}")
    @HystrixCommand(fallbackMethod = "handleCustomerDownTime")
    public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId(@PathVariable Integer accountId){

        RequiredResponse requiredResponse = new RequiredResponse();
        //1st get account details
        Account account = accountRepository.findById(accountId).get();
        requiredResponse.setAccount(account);

        //then get all customers registered to that account
        List<Customer> listOfCustomers = restTemplate.getForObject("http://CUSTOMER-MANAGEMENT-SERVICE/customer/customer/"+accountId, List.class );
        requiredResponse.setCustomers(listOfCustomers);
        return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
    }

    public ResponseEntity<RequiredResponse> handleCustomerDownTime(@PathVariable Integer id) {

        RequiredResponse requiredResponse = new RequiredResponse();
        //1st get account details
        Account account = accountRepository.findById(id).get();
        requiredResponse.setAccount(account);
        return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
    }

     //Delete Account
    @DeleteMapping(path="/account/{accountId}")
    private void deleteAccount(@PathVariable("accountId") int accountId) {
        accountService.delete(accountId);
    }
    
    // checkBalance
    @GetMapping("/account/{accountId}/balance")
    public int getBalance(@PathVariable int accountId) {
        return accountService.getBalance(accountId);
    }

    // depositAmount
    @PutMapping("/account/{accountId}/deposit/{amount}")
    public void depositAmount(@PathVariable int accountId, @PathVariable Integer amount) {
        int initBal = getBalance(accountId);
        accountService.depositAmount(accountId, amount);
        Logger logger = new Logger(accountId, "Deposited", "Success", initBal, initBal + amount);
        loggerController.addLog(logger);
    }

    // withdrawAmount
    @PutMapping("/account/{accountId}/withdraw/{amount}")
    public void withdrawAmount(@PathVariable int accountId, @PathVariable Integer amount) {
        int initBal = getBalance(accountId);
        accountService.withdrawAmount(accountId, amount);
        Logger logger = new Logger(accountId, "Withdrawn", "Success", initBal, initBal - amount);
        loggerController.addLog(logger);
    }

}
