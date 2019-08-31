import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Represents the class used to read data from the file and process it.
 */
public class Parser {

    public Parser() {

    }

    /**
     * Contains the methods to read the input and process it into the form required for the algorithm.
     *
     * @param s The information to be read and processed
     * @param t The list where the information is stored after being processed
     * @throws ParseException If date for event is not in the specified format i.e. MM/dd/yyyy HH:mm
     */
    public void readTask(String s, TaskList t) throws ParseException {
        DateFormat df = new SimpleDateFormat("E, MMM dd yyyy HH:mm");

        if (s.contains("[T]")) {
            if (s.contains( "["+"\u2713"+"]")) {
                t.add(new ToDo(s.substring(7), true));
            }
            else {
                t.add(new ToDo(s.substring(7)));
            }
        }
        else if (s.contains("[E]")) {

            int start = s.indexOf('(');
            String e = s.substring(7,start-1);
            String dl = s.substring(start+5, start+27);
            Date at = df.parse(dl);
            if (s.contains( "["+"\u2713"+"]")) {
                t.add(new Event(e,at, true));
            }
            else {
                t.add(new Event(e,at));
            }
        }
        else {
            int sl = s.indexOf('(');
            String d = s.substring(7, sl - 1);
            String by = s.substring(sl + 4);
            if (s.contains("[" + "\u2713" + "]")) {
                t.add(new Deadline(d, by, true));
            } else {
                t.add(new Deadline(d, by));
            }
        }
    }
}
