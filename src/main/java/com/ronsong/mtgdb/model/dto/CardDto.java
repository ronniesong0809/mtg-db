package com.ronsong.mtgdb.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardDto {
    private String name;
    private int quantity;
}
