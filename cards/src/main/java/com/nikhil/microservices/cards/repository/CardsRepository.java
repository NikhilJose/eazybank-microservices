package com.nikhil.microservices.cards.repository;

import com.nikhil.microservices.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Card,Long> {

    List<Card> findByCustomerId(int customerId);

}
