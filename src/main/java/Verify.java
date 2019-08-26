import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Verify {
    public Verify() {
    }

    public static boolean isNum(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static void checkCommandValidity(String command, TaskList list, DateTimeFormatter formatter)
            throws DukeException {
        if (command.startsWith("done")) {
            if (command.split(" ").length < 2) {
                throw new DukeException("whoops! please enter a number after done");
            } else if (!isNum(command.split(" ")[1])) {
                throw new DukeException("whoops! you need to enter a number, not anything else");
            } else if ((Integer.parseInt(command.split(" ")[1])) >= list.list.size()) {
                throw new DukeException("oh! that task doesn't exist! please enter a task within the list!");
            }
        } else if (command.startsWith("delete")) {
            if (command.split(" ").length < 2) {
                throw new DukeException("whoops! please enter a number after delete");
            } else if (!isNum(command.split(" ")[1])) {
                throw new DukeException("whoops! you need to enter a number, not anything else");
            } else if ((Integer.parseInt(command.split(" ")[1]) - 1) >= list.list.size()) {
                throw new DukeException("oh! that task doesn't exist! please enter a task within the list!");
            }
        } else if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                throw new DukeException("oopsie! did you forget to add a description for your todo?");
            }
        } else if (command.startsWith("deadline")) {
            if (command.length() <= 9) {
                throw new DukeException("oops! did you forget to add a description to your deadline?");
            } else if (command.substring(9).split(" /by ").length < 2) {
                throw new DukeException("oh no! the by date of a deadline cannot be empty!");
            }
            try {
                LocalDateTime.parse(command.substring(9).split(" /by ")[1], formatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("oh dear, your date is invalid! please check if you entered it right!");
            }
        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                throw new DukeException("whoops! did you forget to add a description to your event?");
            } else if (command.substring(6).split(" /at ").length < 2) {
                throw new DukeException("golly! the from/to dates of an event cannot be empty!");
            } else if (command.substring(6).split(" /at ")[1].split(" to ").length < 2) {
                throw new DukeException("gosh! i can't find your from and to dates! did you enter both?");
            }
            try {
                LocalDateTime.parse(command.substring(6).split(" /at ")[1].split(" to ")[0], formatter);
                LocalDateTime.parse(command.substring(6).split(" /at ")[1].split(" to ")[1], formatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("oh dear, your date is invalid! please check if you entered it right!");
            }
        } else if (command.equals("bye") || command.equals("list")) {
        } else {
            throw new DukeException("blast! i don't understand that!");
        }
    }
}