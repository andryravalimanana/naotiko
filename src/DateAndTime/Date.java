package DateAndTime;

import java.time.LocalDate;

/**
 *
 * @author Andry
 */
public class Date {

    private int day;
    private int month;
    private int year;

    public Date(String date) {
        String[] dateSplit = date.split("-");
        this.day = Integer.valueOf(dateSplit[0]);
        this.month = Integer.valueOf(dateSplit[1]);
        this.year = Integer.valueOf(dateSplit[2]);
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        String date = day + "-" + month + "-" + year;
        return date;
    }

    public static Date getNow() {
        LocalDate localDate = LocalDate.now();
        Date date = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        return date;
    }
}
