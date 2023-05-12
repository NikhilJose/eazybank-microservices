package com.nikhil.microservices.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nikhil.microservices.account.config.AccountsServiceConfig;
import com.nikhil.microservices.account.entity.Properties;
import com.nikhil.microservices.account.entity.Account;
import com.nikhil.microservices.account.entity.Customer;
import com.nikhil.microservices.account.repository.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AccountsController {

    private AccountRepository accountRepository;

    private AccountsServiceConfig accountsConfig;

    public AccountsController(AccountRepository accountRepository, AccountsServiceConfig accountsConfig) {
        this.accountRepository = accountRepository;
        this.accountsConfig = accountsConfig;
    }

    @PostMapping("/myAccount")
    public Account getAccountDetails(@RequestBody Customer customer){
        return Optional.ofNullable(accountRepository.findByCustomerId(customer.getCustomerId()))
                .orElse(null);
    }

    @GetMapping("/account/properties")
    public String getPropertyDetails() throws JsonProcessingException{
        ObjectWriter objectWriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties=new Properties(accountsConfig.getMsg(),accountsConfig.getBuildVersion(),accountsConfig.getMailDetails(),accountsConfig.getActiveBranches());
        return objectWriter.writeValueAsString(properties);
    }
}
