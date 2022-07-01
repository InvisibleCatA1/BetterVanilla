package com.invisiblecat.utils;

public class TimeUtils {
    private long lastMs = System.currentTimeMillis();

    public boolean hasTimePassed(long ms, boolean b) {
        ms = lastMs + ms;

        if (b && System.currentTimeMillis() >= ms)
            reset();

        return System.currentTimeMillis() >= ms;
    }

    public void reset() {
        lastMs = System.currentTimeMillis();
    }

    public long timePassed() {
        return System.currentTimeMillis() - lastMs;
    }
}
