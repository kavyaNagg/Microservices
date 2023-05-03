package com.nagarro.accountmanagementservice.services;

import com.nagarro.accountmanagementservice.entity.Account;
import com.nagarro.accountmanagementservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    //Get All Accounts
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<Account>();
        accountRepository.findAll().forEach(accounts1 -> accounts.add(accounts1));
        return accounts;
    }

    //Get Account Details
    public Account getAccountById(int accountId) {
        return accountRepository.findById(accountId).get();
    }

    //Delete Account
    public void delete(int id){
        accountRepository.deleteById(id);
    }

    //Get Balance
    public int getBalance(int accountId) {
        return accountRepository.findBalanceByAcctID(accountId);
    }

    //Deposit Amount
    public void depositAmount(int accountId, Integer amount) {
        accountRepository.saveBalanceByAcctId(accountId, amount);
    }

    //Withdraw Amount
    public void withdrawAmount(int accountId, Integer amount) {
        accountRepository.withdrawAmountByAcctId(accountId, amount);
    }
}
