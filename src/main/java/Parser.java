import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parser {
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("done ")){
            try {
                int id = Integer.parseInt(command.substring(5));
                return new DoneCommand(id);
            } catch  (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Invalid index provided!");
            }

        } else if (command.startsWith("delete ")) {
            try {
                int id = Integer.parseInt(command.substring(7));
                return new DeleteCommand(id);
            } catch  (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Invalid index provided!");
            }
        } else if (command.startsWith("todo ")) {
            return new AddCommand(new Todo(command));
        } else if (command.startsWith("deadline ")) {
            if (!command.contains("/by ")) {
                throw new DukeException("☹ OOPS!!! Please follow format e.g deadline return book /by Sunday");
            } else {
                return new AddCommand(new Deadline(formatTime(command)));
            }
        } else if (command.startsWith("event ")) {
            if (!command.contains("/at ")) {
                throw new DukeException("☹ OOPS!!! Please follow format e.g event project meeting /at Mon 2-4pm");
            } else {
                return new AddCommand(new Event(formatTime(command)));
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String formatTime(String input) throws DukeException {
        StringBuilder temp = new StringBuilder();
        StringBuilder result = new StringBuilder();
        boolean flag = false;

        List<String> formatStrings = Arrays.asList("d/M/y HHmm", "d/M/y", "d/M" );
        List<String> outputFormats = Arrays.asList("MMMMM dd, yyyy, h:mm a", "MMMMM dd, yyyy", "MMMMM dd" );
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (flag) {
                temp.append(c);
            } else {
                result.append(c);
            }
            if (c == '/') {
                flag = true;
            }
        }
        Date date = null;
        for (int i = 0; i < formatStrings.size(); i++)
        {
            try {
                DateFormat df = new SimpleDateFormat(formatStrings.get(i));
                df.setLenient(false);
                date = df.parse(temp.toString().substring(3));
                DateFormat formatter = new SimpleDateFormat(outputFormats.get(i));
                result.append("   ").append(formatter.format(date));
                break;

            } catch (ParseException e) {
                //System.out.println("!");
            }

        }


        if (date == null) {
            throw new DukeException("Time format not acceptable!");
        } else {
            return result.toString();
        }
    }


}
