package com.example.foreahead;

import java.util.ArrayList;
import java.util.List;

public class HelperActivity {
    private static List<ListItemActivity> songsList = new ArrayList<>();
    private static int songCounter = 0;
    //private static int categoryCounter = 0;
    private static int songsNumber = 3;
    private static boolean isResumeRock = false;
//    private static boolean isResumePOP = false

    public static List<ListItemActivity> getSongsList() {
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

    /*public static int getCategoryCounter() {
        return categoryCounter;
    }
    public static void setCategoryCounter(int counter) {
        categoryCounter = counter;
    }*/

    public static int getSongsNumber() { return songsNumber; }
    public static void setSongsNumber(int songsNumber) {
        HelperActivity.songsNumber = songsNumber;
    }
    public static boolean getIsResumeRock() { return isResumeRock; }
    public static void setIsResumeRock(boolean isResumeRock) {HelperActivity.isResumeRock = isResumeRock; }
//    public static boolean getIsResumePOP() { return isResumePOP; }
//    public static void setIsResumePOP(boolean isResumePOP) {HelperActivity.isResumePOP = isResumePOP; }
}

