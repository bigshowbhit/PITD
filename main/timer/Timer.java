package main.timer;

public class Timer {
    private long startTime;
    private long duration;

    public Timer(long duration) {
        this.duration = duration * 1000;
        this.startTime = System.currentTimeMillis();
    }

    public boolean isTimeUp() {
        return System.currentTimeMillis() - startTime >= duration;
    }

    public long getTimeRemaining() {
        long elapsed = System.currentTimeMillis() - startTime;
        return Math.max((duration - elapsed) / 1000, 0); // Return remaining time in seconds
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
    }
}