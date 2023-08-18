package com.ronsong.mtgdb.Service;

import com.ronsong.mtgdb.model.Card;
import com.ronsong.mtgdb.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MtgService {
    @Autowired
    CardsRepository repository;

    public Page<Card> getAllCards(int page) {
        Pageable pageable = PageRequest.of(page, 25);
        return repository.findAll(pageable);
    }

    public List<Card> getCardByName(String name) {
        return repository.findAllByName(name);
    }
}
