package com.nikhil.microservices.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nikhil.microservices.loans.config.LoansServiceConfig;
import com.nikhil.microservices.loans.entity.Loan;
import com.nikhil.microservices.loans.entity.Customer;
import com.nikhil.microservices.loans.entity.Properties;
import com.nikhil.microservices.loans.repository.LoansRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LoansController {

    private LoansRepository loansRepository;

    private LoansServiceConfig loansServiceConfig;

    public LoansController(LoansRepository loansRepository, LoansServiceConfig loansServiceConfig) {
        this.loansRepository = loansRepository;
        this.loansServiceConfig = loansServiceConfig;
    }

    @PostMapping("/myLoans")
    public List<Loan> getLoanDetails(@RequestBody Customer customer) {
        return Optional.ofNullable(loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId()))
                .orElse(null);
    }

    @GetMapping("/loans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansServiceConfig.getMsg(), loansServiceConfig.getBuildVersion(), loansServiceConfig.getMailDetails(), loansServiceConfig.getActiveBranches());
        return objectWriter.writeValueAsString(properties);
    }
}
