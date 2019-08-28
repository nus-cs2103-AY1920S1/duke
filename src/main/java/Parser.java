import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    public Parser() {

    }

    public static String getFormattedDate(String dateAndTime) {
        String date = dateAndTime;
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(dateAndTime);
            String day = new SimpleDateFormat("dd").format(date);
            String month = new SimpleDateFormat("MMMMMMMMMMMMMMM").format(date);
            String year = new SimpleDateFormat("yyyy").format(date);
            String time = new SimpleDateFormat("h:mm a").format(date).toLowerCase();
            String postfix;

            int int_day = Integer.parseInt(day);
            if (int_day >= 11 && int_day <= 13) {
                postfix = "th";
            } else if (int_day % 10 == 1) {
                postfix = "st";
            } else if (int_day % 10 == 2) {
                postfix = "nd";
            } else if (int_day % 10 == 3) {
                postfix = "rd";
            } else {
                postfix = "th";
            }

            date = int_day + postfix + " of " + month + " " + year + ", " + time;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }


}