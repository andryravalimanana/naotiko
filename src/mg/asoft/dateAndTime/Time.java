package mg.asoft.dateAndTime;

import java.time.LocalTime;

/**
 *
 * @author Andry
 */
public class Time {

    private int hour;
    private int min;
    private int sec;

    public Time(String time) {
        String[] timeSplit = time.split(":");
        this.hour = Integer.valueOf(timeSplit[0]);
        this.min = Integer.valueOf(timeSplit[1]);
        this.sec = Integer.valueOf(timeSplit[2]);
    }

    public Time(int hour, int min, int sec) {
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public String toString() {
        return hour + ":" + min + ":" + sec;
    }

    public static Time getNow() {
        LocalTime localTime = LocalTime.now();
        Time time = new Time(localTime.getHour(), localTime.getMinute(), localTime.getSecond());
        return time;
    }
}
