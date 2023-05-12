package com.nikhil.microservices.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nikhil.microservices.cards.config.CardsServiceConfig;
import com.nikhil.microservices.cards.entity.Card;
import com.nikhil.microservices.cards.entity.Customer;
import com.nikhil.microservices.cards.entity.Properties;
import com.nikhil.microservices.cards.repository.CardsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CardsController {

    private CardsRepository cardsRepository;

    private CardsServiceConfig cardsServiceConfig;

    public CardsController(CardsRepository cardsRepository, CardsServiceConfig cardsServiceConfig) {
        this.cardsRepository = cardsRepository;
        this.cardsServiceConfig = cardsServiceConfig;
    }

    @PostMapping("/myCards")
    public List<Card> getLoanDetails(@RequestBody Customer customer) {
        return Optional.ofNullable(cardsRepository.findByCustomerId(customer.getCustomerId()))
                .orElse(null);
    }

    @GetMapping("/cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsServiceConfig.getMsg(), cardsServiceConfig.getBuildVersion(), cardsServiceConfig.getMailDetails(), cardsServiceConfig.getActiveBranches());
        return objectWriter.writeValueAsString(properties);
    }
}
