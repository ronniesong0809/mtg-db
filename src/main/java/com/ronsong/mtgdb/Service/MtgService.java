package com.ronsong.mtgdb.Service;

import com.ronsong.mtgdb.model.dto.Card;
import com.ronsong.mtgdb.model.CardResponse;
import com.ronsong.mtgdb.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MtgService {
    @Autowired
    CardsRepository repository;

    public Page<Card> getAllCards(int page) {
        Pageable pageable = PageRequest.of(page, 25);
        Page<Card> allCards = repository.findAll(pageable);
        // return mappingCards(allCards);
        return allCards;
    }

    public List<CardResponse> getCardByName(String name) {
        List<Card> allByName = repository.findAllByName(name);
        return mappingCards(allByName);
    }

    private static List<CardResponse> mappingCards(List<Card> allByName) {
        log.info("{}", allByName);

        List<CardResponse> cardResponses = new ArrayList<>();
        allByName.forEach(card -> {
            String[] typeLine = card.getType_line().split("—");
            CardResponse response = CardResponse.builder()
                    .id(card.getId())
                    .name(card.getName())
                    .oracle_text(card.getOracle_text())
                    .colors(card.getColors())
                    .type(Arrays.asList(typeLine[0].split("\\s*,\\s*")))
                    .subtype(typeLine.length > 1 ? Arrays.asList(typeLine[1].split("\\s*,\\s*")) : null)
                    .build();
            cardResponses.add(response);
        });

        return cardResponses;
    }
}
