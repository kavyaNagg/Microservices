package com.nagarro.accountmanagementservice.repositories;

import com.nagarro.accountmanagementservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("select currentBalance from Account where accountId = ?1")
    public int findBalanceByAcctID(int accountId);

    public List<Account> findByAccountNumber(Integer id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Account set currentBalance = currentBalance+?2 where accountId=?1")
    public void saveBalanceByAcctId(int accountId, int currentBalance);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Account set currentBalance = currentBalance+?2 where accountId=?1")
    public void withdrawAmountByAcctId(int accountId, int currentBalance);
}
