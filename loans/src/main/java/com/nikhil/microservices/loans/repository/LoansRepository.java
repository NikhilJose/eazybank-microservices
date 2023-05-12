package com.nikhil.microservices.loans.repository;

import com.nikhil.microservices.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loan,Long> {

    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);

}
