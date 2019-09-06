package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

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

    public String parseDesc(String s) {
        String cmd = parseCommand(s);
        int index = 0;
        switch (cmd) {
            case "deadline":
                index = s.indexOf("/");
                return s.substring(9, index - 1);
            case "event":
                index = s.indexOf("/");
                return s.substring(6, index - 1);
            case "todo":
                return s.substring(5);
            default:
                return "No description provided";
        }
    }

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
