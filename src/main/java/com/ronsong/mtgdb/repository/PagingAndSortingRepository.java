package com.ronsong.mtgdb.repository;

import com.ronsong.mtgdb.model.dto.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagingAndSortingRepository extends MongoRepository<Card, String> {
    Page<Card> findAll(Pageable pageable);
    List<Card> findAllByName(String name);
}
