package com.ronsong.mtgdb.model.stats;

import java.util.ArrayList;

public interface Type {
    public void setTotal(int total);

    public void setAveragePower(float power);
    public void setAverageToughness(float toughness);
    public void setAverageMana(float mana);

    public void setNames(ArrayList<String> names);

    public int getTotal();
    public float getAveragePower();
    public float getAverageToughness();
    public float getAverageMana();

    public ArrayList<String> getNames();

    public String getClassNames();
}
