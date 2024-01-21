package com.example.foreahead;

import java.util.ArrayList;
import java.util.List;

public class HelperActivity {
    private static List<ListItemActivity> songsList = new ArrayList<>();
    private static List<Integer> categoryList = new ArrayList<>();
    private static int songCounter = 0;
    private static int categoryCounter = 0;
    private static int songsNumber = 10;
    private static int result = 0;
    private static boolean isResumeRock = false;
    private static boolean isResumePOP = false;
    private static boolean isResumeDisco = false;
    private static boolean isResumeOld = false;
    private static boolean isResumeTV = false;


    public static List<ListItemActivity> getSongsList() {
        return songsList;
    }
    public static void setSongsList(List<ListItemActivity> list) {
        songsList = list;
    }
    public static List<Integer> getCategoryList() {
        return categoryList;
    }
    public static void setCategoryList(List<Integer> list) {
        categoryList = list;
    }

    public static int getSongCounter() {
        return songCounter;
    }
    public static void setSongCounter(int counter) {
        songCounter = counter;
    }
    public static int getCategoryCounter() {
        return categoryCounter;
    }
    public static void setCategoryCounter(int counter) {
        categoryCounter = counter;
    }
    public static int getSongsNumber() { return songsNumber; }
    public static void setSongsNumber(int songsNumber) {
        HelperActivity.songsNumber = songsNumber;
    }

    public static int getResult() {
        return result;
    }
    public static void setResult(int counter) {result = result; }

    public static boolean getIsResumeRock() { return isResumeRock; }
    public static void setIsResumeRock(boolean isResumeRock) {HelperActivity.isResumeRock = isResumeRock; }
    public static boolean getIsResumePOP() { return isResumePOP; }
    public static void setIsResumePOP(boolean isResumePOP) {HelperActivity.isResumePOP = isResumePOP; }
    public static boolean getIsResumeDisco() { return isResumeDisco; }
    public static void setIsResumeDisco(boolean isResumeDisco) {HelperActivity.isResumeDisco = isResumeDisco; }
    public static boolean getIsResumeOld() { return isResumeOld; }
    public static void setIsResumeOld(boolean isResumeOld) {HelperActivity.isResumeOld = isResumeOld; }
    public static boolean getIsResumeTV() { return isResumeTV; }
    public static void setIsResumeTV(boolean isResumeTV) {HelperActivity.isResumeTV = isResumeTV; }
}

