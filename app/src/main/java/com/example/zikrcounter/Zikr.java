package com.example.zikrcounter;

public class Zikr {
    private String zikr;
    private int noOfTimesZikr;

    public Zikr(String zikr, int noOfTimesZikr) {
        this.zikr = zikr;
        this.noOfTimesZikr = noOfTimesZikr;
    }

    public String getZikr() {
        return zikr;
    }

    public void setZikr(String zikr) {
        this.zikr = zikr;
    }

    public int getNoOfTimesZikr() {
        return noOfTimesZikr;
    }

    public void setNoOfTimesZikr(int noOfTimesZikr) {
        this.noOfTimesZikr = noOfTimesZikr;
    }
}
