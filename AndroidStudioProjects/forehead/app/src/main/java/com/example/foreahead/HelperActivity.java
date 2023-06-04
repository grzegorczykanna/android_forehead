package com.example.foreahead;

import java.util.ArrayList;
import java.util.List;

public class HelperActivity {
    private static List<ListItemActivity> songsList = new ArrayList<>();
    private static List<Integer> randomSongs = new ArrayList<>();
    private static int songCounter = 0;

    public static List<ListItemActivity> getSongsListList() {
        return songsList;
    }
    public static void setSongsList(List<ListItemActivity> list) {
        songsList = list;
    }

    public static List<Integer> getRandomSongs() {
        return randomSongs;
    }
    public static void setRandomSongs(List<Integer> list) {
        randomSongs = list;
    }

    public static int getCounter() {
        return songCounter;
    }
    public static void setCounter(int counter) {
        songCounter = counter;
    }

}

