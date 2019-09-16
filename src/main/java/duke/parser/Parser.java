package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A parser that interprets user input.
 */
public class Parser {

    /**
     * Interprets the type of command from user input.
     *
     * @param s user input text.
     * @return the type of command.
     */
    public String parseCommand(String s) {
        if (s.toLowerCase().equals("bye")) {
            return "bye";
        } else if (s.toLowerCase().equals("list")) {
            return "list";
        } else if (s.toLowerCase().startsWith("todo")) {
            return "todo";
        } else if (s.toLowerCase().startsWith("done")) {
            return "done";
        } else if (s.toLowerCase().startsWith("find")) {
            return "find";
        } else if (s.toLowerCase().startsWith("event")) {
            return "event";
        } else if (s.toLowerCase().startsWith("delete")) {
            return "delete";
        } else if (s.toLowerCase().startsWith("deadline")) {
            return "deadline";
        } else {
            return "unknown command detected";
        }
    }

    /**
     * Interprets the description of a task from user input.
     *
     * @param s user input text.
     * @return the description of that particular task.
     */
    public String parseDesc(String s) {
        String cmd = parseCommand(s);
        int index = 0;
        switch (cmd) {
        case "deadline":
            index = s.indexOf("/");
            return s.substring(9, index - 1).trim();
        case "event":
            index = s.indexOf("/");
            return s.substring(6, index - 1).trim();
        case "todo":
        case "find":
            return s.substring(5).trim();
        default:
            return "No description provided";
        }
    }

    /**
     * Interprets the date of a task from user input.
     *
     * @param s user input text.
     * @return the date of that particular task.
     */
    public String parseDate(String s) {
        int index = s.indexOf("/");
        String rawDate = s.substring(index + 4).trim();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            Date d = sdf.parse(rawDate);
            return d.toString();
        } catch (ParseException e) {
            return rawDate;
        }
    }
}
