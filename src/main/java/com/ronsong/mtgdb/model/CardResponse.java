package com.ronsong.mtgdb.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CardResponse {
    private String id;
    private String oracle_id;
    private String name;
    private List<String> colors;
    private List<String> type;
    private List<String> subtype;
    private String oracle_text;
    private String mana_cost;
    private int mana_total;
    private String power;
    private String toughness;
    private String released_at;
    private String lang;
    private List<CardResponse> alternatives;
}
