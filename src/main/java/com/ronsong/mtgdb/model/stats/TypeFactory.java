package com.ronsong.mtgdb.model.stats;

import com.ronsong.mtgdb.model.stats.type.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Slf4j
@Component
public class TypeFactory {
    HashMap<String, Type> types = new HashMap<String, Type>();

    public TypeFactory() {
        types.put(Creature.class.getSimpleName(), new Creature());
        types.put(Artifact.class.getSimpleName(), new Artifact());
        types.put(Enchantment.class.getSimpleName(), new Enchantment());
        types.put(Land.class.getSimpleName(), new Land());
        types.put(Planeswalker.class.getSimpleName(), new Planeswalker());
        types.put(Instant.class.getSimpleName(), new Instant());
        types.put(Sorcery.class.getSimpleName(), new Sorcery());
    }

    public Type getTypes(String name) {
        return types.get(name);
    }
}
