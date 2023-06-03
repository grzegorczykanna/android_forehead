package com.example.foreahead;

import java.util.ArrayList;
import java.util.List;

public class HelperActivity {
    private static List<Integer> myList = new ArrayList<>();
    private static int songCounter = 0;

    public static List<Integer> getMyList() {
        return myList;
    }

    public static void setMyList(List<Integer> list) {
        myList = list;
    }

    public static int getCounter() {
        return songCounter;
    }

    public static void setCounter(int counter) {
        songCounter = counter;
    }
}
