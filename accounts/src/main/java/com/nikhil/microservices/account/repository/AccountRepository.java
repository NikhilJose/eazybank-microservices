package com.nikhil.microservices.account.repository;

import com.nikhil.microservices.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByCustomerId(int customerId);
}
