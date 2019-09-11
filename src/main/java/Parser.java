import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * Represents a parser that makes sense of user commands.
 */
public class Parser {

    /**
     * Returns parsed command from given user command.
     * Parsed command is represented by an array that contains
     * command type and command details.
     *
     * @param input User command.
     * @return Parsed command.
     */
    public static String[] parseCommand(String input) {
        String[] parseInfo = new String[2];

        if (input.matches("(done|delete)\\s+\\d+|(todo|deadline|event|find|set)\\s+.+")) {
            parseInfo = input.split("\\s+", 2);
        } else if (input.equals("list")) {
            parseInfo[0] = "list";
        } else {
            parseInfo[0] = "unknown";
        }

        return parseInfo;
    }

    /**
     * Returns parsed details from given command details.
     * Parsed details represented by array that contains task description
     * and task date/time information.
     *
     * @param input Command details.
     * @return Parsed details.
     */
    public static String[] parseDetails(String input) {
        String[] parseInfo;

        if (input.contains("/by")) {
            parseInfo = input.split("\\s+/by\\s+");
        } else {
            parseInfo = input.split("\\s+/at\\s+");
        }

        parseInfo[1] = convertDate(parseInfo[1]);

        return parseInfo;
    }

    /**
     * Returns date/time in text format from date/time in numerical format.
     *
     * @param date Numerical date/time.
     * @return Textual date/time.
     */
    public static String convertDate(String date) {
        SimpleDateFormat numDateTime = new SimpleDateFormat("d/M/y HHmm");
        SimpleDateFormat textDateTime = new SimpleDateFormat("d MMMM y, h.mma");

        try {
            if (date.matches("\\d+/\\d+/\\d+\\s+\\d+")) {
                Date d = numDateTime.parse(date);
                date = textDateTime.format(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }
}