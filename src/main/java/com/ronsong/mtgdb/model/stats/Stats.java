package com.ronsong.mtgdb.model.stats;

import com.ronsong.mtgdb.model.Card;
import com.ronsong.mtgdb.model.CardResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class Stats {
    private List<CardResponse> cardsDto;
    private float averageMana;
    private int creatureNum;
    private Map<String, Type> types;
}
