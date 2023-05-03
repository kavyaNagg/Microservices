package com.nagarro.accountmanagementservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private int accountId;

    private int accountNumber;

    private String accountHolderName;

//    private String accountName;

    private double currentBalance;


}
