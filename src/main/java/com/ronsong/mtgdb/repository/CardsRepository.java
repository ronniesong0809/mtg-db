package com.ronsong.mtgdb.repository;

import com.ronsong.mtgdb.model.dto.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends MongoRepository<Card, String> {
    List<Card> findAllByName(String name);
}
