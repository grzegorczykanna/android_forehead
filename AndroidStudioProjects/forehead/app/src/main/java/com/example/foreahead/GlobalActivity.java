package com.example.foreahead;

import java.util.ArrayList;
import java.util.List;

public class GlobalActivity {
    private static List<Integer> myList = new ArrayList<>();

    public static List<Integer> getMyList() {
        return myList;
    }

    public static void setMyList(List<Integer> list) {
        myList = list;
    }
}
