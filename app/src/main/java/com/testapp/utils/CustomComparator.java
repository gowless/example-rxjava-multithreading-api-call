package com.testapp.utils;

import com.testapp.model.Item;

import java.util.Comparator;

public class CustomComparator implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getScore().compareTo(o2.getScore());
    }
}
