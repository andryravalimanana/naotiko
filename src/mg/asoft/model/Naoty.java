package mg.asoft.model;

import mg.asoft.dateAndTime.Date;
import mg.asoft.dateAndTime.Time;

/**
 *
 * @author Andry
 */
public class Naoty {

    private int id;
    private Date date;
    private Time time;
    private String title;
    private String firstKeyword;

    public Naoty() {
        System.out.println("Naoty instance!");
    }

    public Naoty(String title) {
        this.title = title;
        this.firstKeyword = title;
        this.date = Date.getNow();
        this.time = Time.getNow();
    }

    
    public Naoty(int id, Date date, Time time, String title, String firstKeyword) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.title = title;
        this.firstKeyword = firstKeyword;
    }

    public Naoty(int id, String title, String firstKeyword) {
        this.id = id;
        this.title = title;
        this.firstKeyword = firstKeyword;
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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstKeyword() {
        return firstKeyword;
    }

    public void setFirstKeyword(String firstKeyword) {
        this.firstKeyword = firstKeyword;
    }
}
