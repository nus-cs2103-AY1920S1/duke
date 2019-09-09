import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Checks all the possible errors that can occur during entry of command and
 * throws DukeException with message
 */
public class Verify {
    public Verify() {
    }

    /**
     * 
     * @param str the input string
     * @return returns if the string is a number
     */
    public static boolean isNum(String str) {
        assert str != null;
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param command   the string command entered by user
     * @param list      the task list to compare with for checking validity
     * @param formatter date formatter to catch wrong date format exceptions
     * @throws DukeException in the case of invalid input
     */
    public static String checkCommandValidity(String command, TaskList list, DateTimeFormatter formatter) {
        String message;
        assert command != null;
        if (command.startsWith("done")) {
            if (command.split(" ").length < 2) {
                message = "whoops! please enter a number after done";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            } else if (!isNum(command.split(" ")[1])) {
                message = "whoops! you need to enter a number, not anything else";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            } else if ((Integer.parseInt(command.split(" ")[1])) >= list.list.size()) {
                message = "oh! that task doesn't exist! please enter a task within the list!";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            }
        } else if (command.startsWith("delete")) {
            if (command.split(" ").length < 2) {
                message = "whoops! please enter a number after delete";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            } else if (!isNum(command.split(" ")[1])) {
                message = "whoops! you need to enter a number, not anything else";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            } else if ((Integer.parseInt(command.split(" ")[1]) - 1) >= list.list.size()) {
                message = "oh! that task doesn't exist! please enter a task within the list!";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            }
        } else if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                message = "oopsie! did you forget to add a description for your todo?";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            }
        } else if (command.startsWith("deadline")) {
            if (command.length() <= 9) {
                message = "oops! did you forget to add a description to your deadline?";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            } else if (command.substring(9).split(" /by ").length < 2) {
                message = "oh no! the by date of a deadline cannot be empty!";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            }
            try {
                LocalDateTime.parse(command.substring(9).split(" /by ")[1], formatter);
            } catch (DateTimeParseException e) {
                message = "oh dear, your date is invalid! please check if you entered it right!";
                try {
                    throw new DukeException(message);
                } catch (DukeException ex) {
                    return message;
                }
            }
        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                message = "whoops! did you forget to add a description to your event?";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            } else if (command.substring(6).split(" /at ").length < 2) {
                message = "golly! the from/to dates of an event cannot be empty!";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            } else if (command.substring(6).split(" /at ")[1].split(" to ").length < 2) {
                message = "gosh! i can't find your from and to dates! did you enter both?";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            }
            try {
                LocalDateTime.parse(command.substring(6).split(" /at ")[1].split(" to ")[0], formatter);
                LocalDateTime.parse(command.substring(6).split(" /at ")[1].split(" to ")[1], formatter);
            } catch (DateTimeParseException e) {
                message = "oh dear, your date is invalid! please check if you entered it right!";
                try {
                    throw new DukeException(message);
                } catch (DukeException ex) {
                    return message;
                }
            }
        } else if (command.startsWith("find")) {
            if (command.length() <= 5) {
                message = "darn it! you forgot to enter a search term!";
                try {
                    throw new DukeException(message);
                } catch (DukeException e) {
                    return message;
                }
            }
        } else if (command.equals("bye") || command.equals("list")) {
        } else {
            message = "blast! i don't understand that!";
            try {
                throw new DukeException(message);
            } catch (DukeException e) {
                return message;
            }
        }
        message = "ok";
        return message;
    }
}