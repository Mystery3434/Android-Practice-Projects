package com.example.android.wysocamppart1;

public class Item
{
    private String typeOfItem;
    private int relativeProbability;

    public Item(String s, int p)
    {
        typeOfItem = s;
        relativeProbability = p;
    }

    public String getTypeOfItem() {
        return typeOfItem;
    }

    public int getRelativeProbability() {
        return relativeProbability;
    }
}
