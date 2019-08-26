import java.util.*;
import java.text.*;


public class Event extends Task {

    protected String at;
    Date date1;
    Date date2;
    //String x = "2/12/2019 1800";


    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.symbol = "E";
<<<<<<< HEAD
=======
    }

    public void getDate() throws ParseException{
        String[] date_arr = at.split(" ");
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = format.parse(date_arr[0]);
        Date date2 = format.parse(date_arr[0]);
        date1.setHours(Integer.parseInt(date_arr[1])/100);
        date1.setMinutes(Integer.parseInt(date_arr[1])%100);
        date2.setHours(Integer.parseInt(date_arr[2])/100);
        date2.setMinutes(Integer.parseInt(date_arr[2])%100);
>>>>>>> branch-level-8
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}