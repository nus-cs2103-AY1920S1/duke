import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public static String[] parseCommand(String input) {
        String[] parseInfo = new String[2];
        if (input.matches("done\\s+\\d+")) {
            parseInfo[0] = "DONE";
            parseInfo[1] = input.replaceAll("done\\s+", "");
        } else if (input.matches("delete\\s+\\d+")) {
            parseInfo[0] = "DELETE";
            parseInfo[1] = input.replaceAll("delete\\s+", "");
        } else if (input.matches("todo\\s+.+")) {
            parseInfo[0] = "TODO";
            parseInfo[1] = input.replaceAll("todo\\s+", "");
        } else if (input.matches("deadline\\s+.+")) {
            parseInfo[0] = "DEADLINE";
            parseInfo[1] = input.replaceAll("deadline\\s+", "");
        } else if (input.matches("event\\s+.+")) {
            parseInfo[0] = "EVENT";
            parseInfo[1] = input.replaceAll("event\\s+", "");
        } else if (input.equals("list")) {
            parseInfo[0] = "LIST";
        } else {
            parseInfo[0] = "UNKNOWN";
        }

        return parseInfo;
    }

    public static String[] parseDetails(String input) {
        String[] parseInfo;

        if (input.contains("/by")) {
            parseInfo = input.split("\\s+/by\\s+");
        } else {
            parseInfo = input.split("\\s+/at\\s+");
        }

        parseInfo[1] = dateConversion(parseInfo[1]);

        return parseInfo;
    }

    public static String dateConversion(String s) {
        SimpleDateFormat numDateTime = new SimpleDateFormat("d/M/y HHmm");
        SimpleDateFormat textDateTime = new SimpleDateFormat("d MMMM y, h.mma");
        try {
            if (s.matches("\\d+/\\d+/\\d+\\s+\\d+")) {
                Date d = numDateTime.parse(s);
                s = textDateTime.format(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }
}