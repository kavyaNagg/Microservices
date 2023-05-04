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
public class Logger {

    @Id
    private int accountId;
    private String transacType;
    private String transacStatus;
    private double initBal;
    private double finalBal;
}
