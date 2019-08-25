import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {
    public static String filePath;
    public static ArrayList<SimpleDateFormat> dateFormats = new ArrayList<>() {
        {

            add(new SimpleDateFormat("dd/M/yyyy HH:mm"));
            add(new SimpleDateFormat("dd-M-yyyy HH:mm"));
            add(new SimpleDateFormat("dd.M.yyyy HH:mm"));
            add(new SimpleDateFormat("dd/M/yyyy hh:mm"));
            add(new SimpleDateFormat("dd-M-yyyy hh:mm"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm"));
            add(new SimpleDateFormat("dd/M/yyyy h a"));
            add(new SimpleDateFormat("dd-M-yyyy h a"));
            add(new SimpleDateFormat("dd.M.yyyy h a"));
            add(new SimpleDateFormat("dd/M/yyyy hhmm a"));
            add(new SimpleDateFormat("dd-M-yyyy hhmm a"));
            add(new SimpleDateFormat("dd.M.yyyy hhmm a"));
            add(new SimpleDateFormat("dd/M/yyyy HHmm"));
            add(new SimpleDateFormat("dd-M-yyyy HHmm"));
            add(new SimpleDateFormat("dd.M.yyyy HHmm"));
            add(new SimpleDateFormat("dd/M/yyyy Hmm"));
            add(new SimpleDateFormat("dd-M-yyyy Hmm"));
            add(new SimpleDateFormat("dd.M.yyyy Hmm"));
            add(new SimpleDateFormat("dd/M/yyyy hhmm"));
            add(new SimpleDateFormat("dd-M-yyyy hhmm"));
            add(new SimpleDateFormat("dd.M.yyyy hhmm"));
            add(new SimpleDateFormat("dd MMM yyyy HHmm"));
            add(new SimpleDateFormat("dd MMMM yyyy HHmm"));
            add(new SimpleDateFormat("dd/M/yyyy"));
            add(new SimpleDateFormat("dd-M-yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("dd MMM yyyy"));
            add(new SimpleDateFormat("dd MMMM yyyy"));
        }
    };

    public static Command parse(String userInput) {
        if ("bye".equals(userInput)) {
            return new ExitCommand(filePath, null);
        }
        String[] inputSplit = userInput.split(" ");
        switch (inputSplit[0]) {
        case "list":
            return new ListCommand(filePath, null);
        case "done":
            return new DoneCommand(filePath, inputSplit);
        case "todo":
            return new AddCommand("todo", userInput, inputSplit, filePath);
        case "deadline":
            return new AddCommand("deadline", userInput, inputSplit, filePath);
        case "event":
            return new AddCommand("event", userInput, inputSplit, filePath);
        case "delete":
            return new DeleteCommand(filePath, inputSplit);
        default:
            // Exception if invalid instruction
            Ui ui = new Ui();
            ui.printError(ui.separationLine
                    + "\n     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + ui.separationLine + "\n");
            return null;
        }
    }

    public static Date convertToDate(String dateString, ArrayList<SimpleDateFormat> dateFormats) {
        Date date = null;
        for (SimpleDateFormat sdf : dateFormats) {
            try {
                sdf.setLenient(false);
                date = sdf.parse(dateString);
            } catch (ParseException pe) {
                // Continue checking for matching date format
            }
            if (date != null) break;
        }
        return date;
    }

    public static void setFilePath(String filePath) {
        Parser.filePath = filePath;
    }
}
