package com.ronsong.mtgdb.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CardResponse {
    private String id;
    private String name;
    private List<String> colors;
    private String oracle_text;
    private List<String> type;
    private List<String> subtype;
    private String uri;
}
