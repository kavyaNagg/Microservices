package com.nagarro.accountmanagementservice.model;

import com.nagarro.accountmanagementservice.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequiredResponse {

    private Account account;

    private List<Customer> customers;
}
