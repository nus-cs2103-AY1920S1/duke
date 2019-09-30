package com.tysng.duke.util;

public class Index {
    int zeroBasedIndex;
    public Index(int oneBasedIndex) {
        this.zeroBasedIndex = oneBasedIndex -1;
    }

    public int getIndex() {
        return zeroBasedIndex;
    }
}
