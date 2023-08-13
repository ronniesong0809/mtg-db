package com.ronsong.mtgdb.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Card {
    private String id;
    private String name;
    private List<String> colors;
    private String oracle_text;
    private String type_line;
    private String scryfall_uri;
}
