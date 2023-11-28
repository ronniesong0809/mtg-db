package com.ronsong.mtgdb.Service;

import com.ronsong.mtgdb.common.Constants;
import com.ronsong.mtgdb.model.Card;
import com.ronsong.mtgdb.model.CardResponse;
import com.ronsong.mtgdb.model.stats.Stats;
import com.ronsong.mtgdb.model.dto.CardDto;
import com.ronsong.mtgdb.model.stats.Type;
import com.ronsong.mtgdb.model.stats.TypeFactory;
import com.ronsong.mtgdb.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public Stats getCardsByDeck(List<CardDto> cardsDto) {
        Stats stats = new Stats();
        stats.setTypes(new HashMap<>());
        List<CardResponse> cards = new ArrayList<>();

        cardsDto.forEach(dto -> {
            Card card = repository.findTopByNameOrderByIdDesc(dto.getName());
            if ("en".equals(card.getLang())) {
                CardResponse cardResponse = mappingCard(card);
                cards.add(cardResponse);

                buildTypeObject(dto, cardResponse, stats);
            }
        });
        stats.setCardsDto(cards);
        return stats;
    }

    private static CardResponse mappingCard(Card card) {
        return CardResponse.builder()
                .id(card.getId())
                .oracle_id(card.getOracle_id())
                .name(card.getName())
                .colors(card.getColors())
                .type(card.getType())
                .subtype(card.getSubtype())
                .oracle_text(card.getOracle_text())
                .mana_cost(card.getMana_cost())
                .mana_total(getMana(card.getMana_cost()))
                .power("*".equals(card.getPower())
                        ? 0
                        : Integer.parseInt(Optional.ofNullable(card.getPower())
                        .orElse("0"))
                )
                .toughness("*".equals(card.getToughness())
                        ? 0
                        : Integer.parseInt(Optional.ofNullable(card.getToughness())
                        .orElse("0"))
                )
                .released_at(card.getReleased_at())
                .lang(card.getLang())
                .alternatives(null)
                .build();
    }

    private void buildTypeObject(CardDto dto, CardResponse card, Stats stats) {
        if (card == null || card.getType().isEmpty()) {
            return;
        }

        card.getType().forEach(t -> {
            Type type = stats.getTypes().get(t);
            if (type == null) {
                TypeFactory factory = new TypeFactory();
                type = factory.getTypes(t);
            }

            if (type == null) {
                log.info("Type {} does not exists in factory", t);
                return;
            }

            log.info("Type {} exist in factory", t);
            int mana = card.getMana_total();

            if (type.getNames() == null) {
                type.setNames(new ArrayList<>(Collections.singletonList(dto.getName())));
            } else {
                type.getNames().add(dto.getName());
            }
            type.setAveragePower((type.getAveragePower() * (type.getNames().size() - 1) + card.getPower()) / type.getNames().size());
            type.setAverageToughness((type.getAverageToughness() * (type.getNames().size() - 1) + card.getToughness()) / type.getNames().size());
            type.setAverageMana((type.getAverageMana() * (type.getNames().size() - 1) + mana) / type.getNames().size());
            type.setTotal(type.getTotal() + mana);
            stats.getTypes().put(type.getClassNames(), type);
        });
    }

    private static int getMana(String mana) {
        if (mana == null) {
            return 0;
        }

        List<String> matchList = new ArrayList<>();
        Pattern regex = Pattern.compile("\\{(.*?)\\}");
        Matcher regexMatcher = regex.matcher(mana);

        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group(1));
        }
        log.info("matchList: {}", matchList);

        int result = 0;
        for (String str : matchList) {
            if (str.chars().allMatch(Character::isDigit)) {
                result += Integer.parseInt(str);
            } else {
                try {
                    Constants.Colors.valueOf(str);
                    result += 1;
                } catch (IllegalArgumentException e) {
                    log.error("Colors.valueOf({})", str, e);
                }
            }
        }
        return result;
    }
}
