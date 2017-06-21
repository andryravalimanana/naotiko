package model;

import DateAndTime.Date;
import DateAndTime.Time;

/**
 *
 * @author Andry
 */
public class Naoty {

    private int id;
    private Date date;
    private Time time;
    private String Title;
    private String FirstKeyword;

    public Naoty() {
        System.out.println("Naoty instance!");
    }

    public Naoty(String Title) {
        this.Title = Title;
        this.FirstKeyword = Title;
        this.date = Date.getNow();
        this.time = Time.getNow();
    }

    
    public Naoty(int id, Date date, Time time, String Title, String FirstKeyword) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.Title = Title;
        this.FirstKeyword = FirstKeyword;
    }

    public Naoty(int id, String Title, String FirstKeyword) {
        this.id = id;
        this.Title = Title;
        this.FirstKeyword = FirstKeyword;
        this.date = Date.getNow();
        this.time = Time.getNow();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getFirstKeyword() {
        return FirstKeyword;
    }

    public void setFirstKeyword(String FirstKeyword) {
        this.FirstKeyword = FirstKeyword;
    }
}
