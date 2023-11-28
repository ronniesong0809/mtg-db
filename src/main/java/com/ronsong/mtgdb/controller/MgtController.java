package com.ronsong.mtgdb.controller;

import com.ronsong.mtgdb.Service.MtgService;
import com.ronsong.mtgdb.model.Card;
import com.ronsong.mtgdb.model.stats.Stats;
import com.ronsong.mtgdb.model.dto.CardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api", produces = "application/json")
public class MgtController {
    @Autowired
    private MtgService service;

    @GetMapping(value = "/all")
    public ResponseEntity<Page<Card>> getAllCards(@RequestParam(value = "page", defaultValue = "1") int page) {
        log.info("GET /all");

        Page<Card> allCards = service.getAllCards(page);
        return new ResponseEntity<>(allCards, HttpStatus.OK);
    }

    @GetMapping(value = "/card")
    public ResponseEntity<List<Card>> getCardByName(@RequestParam("name") String name) {
        log.info("GET /card, name: {}", name);

        List<Card> cardByName = service.getCardByName(name);
        return new ResponseEntity<>(cardByName, HttpStatus.OK);
    }

    @PostMapping(value = "/deck")
    public ResponseEntity<Stats> getCardsByDeck(@RequestBody List<CardDto> cardDto) {
        log.info("POST /deck, size: {}", cardDto.size());

        Stats stats = service.getCardsByDeck(cardDto);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
