package com.ronsong.mtgdb.controller;

import com.ronsong.mtgdb.Service.MtgService;
import com.ronsong.mtgdb.model.CardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<CardResponse>> getAllCards() {
        log.info("GET /all");
        return new ResponseEntity<>(service.getAllCards(), HttpStatus.OK);
    }

    @GetMapping(value = "/card")
    public ResponseEntity<List<CardResponse>> getCardByName(@RequestParam("name") String name) {
        log.info("GET /card, name: {}", name);
        return new ResponseEntity<>(service.getCardByName(name), HttpStatus.OK);
    }
}
