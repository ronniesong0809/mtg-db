package com.ronsong.mtgdb.model.stats;

import java.util.ArrayList;
import java.util.List;

public interface Type {
    public void setTotal(int total);

    public void setAverage(float average);

    public void setNames(ArrayList<String> names);

    public int getTotal();

    public float getAverage();

    public ArrayList<String> getNames();

    public String getClassNames();
}
