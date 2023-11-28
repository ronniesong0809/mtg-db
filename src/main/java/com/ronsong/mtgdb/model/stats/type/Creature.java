package com.ronsong.mtgdb.model.stats.type;

import com.ronsong.mtgdb.model.stats.Type;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class Creature implements Type {
    private int total;
    private float average;
    private ArrayList<String> names;

    @Override
    public String getClassNames() {
        return this.getClass().getSimpleName();
    }
}
