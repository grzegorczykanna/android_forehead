package com.example.foreahead;

import java.util.ArrayList;
import java.util.List;

public class HelperActivity {
    private static List<ListItemActivity> songsList = new ArrayList<>();
    private static int songCounter = 0;
    private static int songsNumber = 3;

    public static List<ListItemActivity> getSongsListList() {
        return songsList;
    }
    public static void setSongsList(List<ListItemActivity> list) {
        songsList = list;
    }

    public static int getSongCounter() {
        return songCounter;
    }
    public static void setSongCounter(int counter) {
        songCounter = counter;
    }

    public static int getSongsNumber() { return songsNumber; }
    public static void setSongsNumber(int songsNumber) {
        HelperActivity.songsNumber = songsNumber;
    }
}

