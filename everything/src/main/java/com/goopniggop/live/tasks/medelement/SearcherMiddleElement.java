package com.goopniggop.live.tasks.medelement;

import java.util.LinkedList;

public class SearcherMiddleElement implements Searchable<LinkedList<Integer>, Integer> {
    @Override
    public Integer findIn(LinkedList<Integer> data) {
        int mid = -1;
        for (int i = 0, j = 0; j < data.size(); i++, j = j + 2) {
            mid = data.get(i);
        }
        return mid;
    }
}
