package mg.asoft.study;

/**
 *
 * @author Andry
 */
public class Split {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String date = "12:2:2019";
        String [] dateSplit = date.split(":");
        int day = Integer.valueOf(dateSplit[0]);
        int month = Integer.valueOf(dateSplit[1]);
        int year = Integer.valueOf(dateSplit[2]);
        
        System.out.println("Day: "+ day);
        System.out.println("Month: "+ month);
        System.out.println("Year: "+ year);
    }
    
}
