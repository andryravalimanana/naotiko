package mg.asoft.model;

/**
 *
 * @author Andry
 */
public class Keyword {

    private int id;
    private String title;
    private int idNaoty;

    public Keyword() {
        System.out.println("Keyword instance");
    }

    public Keyword(int id, String title, int idNaoty) {
        this.id = id;
        this.title = title;
        this.idNaoty = idNaoty;
    }

    public Keyword(String title, int idNaoty) {
        this.title = title;
        this.idNaoty = idNaoty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdNaoty() {
        return idNaoty;
    }

    public void setIdNaoty(int idNaoty) {
        this.idNaoty = idNaoty;
    }
}
