package seedu.duke.util;

import seedu.duke.commands.*;
import seedu.duke.exceptions.DukeException;

public class Parser {

    /**
     * Parses commands from the user. Transfers the line into logic that will be executed by the commands.
     *
     * @param input String input from the user.
     * @return Command corresponding to the first word in user input.
     * @throws DukeException Throws if user does not input any existing keyword.
     */
    public static Command parse(String input) throws DukeException {
        String[] keywords = input.split(" ");
        try {
            if (keywords[0].equals("bye")) {
                return new ByeCommand();
            } else if (keywords[0].equals("list")) {
                return new ListCommand();
            } else if (keywords[0].equals("done")) {
                if (keywords.length > 2) {
                    throw new DukeException("☹ OOPS!!! You supplied too many arguments :-(");
                } else {
                    return new DoneCommand(Integer.parseInt(keywords[1]));
                }
            } else if (keywords[0].equals("todo")) {
                String temp = parseTodo(keywords);
                return new TodoCommand(temp);
            } else if (keywords[0].equals("deadline")) {
                String[] temp = parseTaskTime(keywords, "deadline");
                return new DeadlineCommand(temp[0], temp[1]);
            } else if (keywords[0].equals("event")) {
                String[] temp = parseTaskTime(keywords, "event");
                return new EventCommand(temp[0], temp[1]);
            } else if (keywords[0].equals("delete")) {
                if (keywords.length > 2) {
                    throw new DukeException("☹ OOPS!!! You supplied too many arguments :-(");
                } else {
                    return new DeleteCommand(Integer.parseInt(keywords[1]));
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("☹ OOPS!!! I can't mark or delete without an entry :-(");
        }
    }

    /**
     * Parses the todo command to see if it has a valid input.
     *
     * @param keywords String array consisting of "todo" as entry 0 and the rest of the message for the rest.
     * @return A string consisting of the original input string but without "todo".
     * @throws DukeException Throws if user did not supply a description for Todo.
     */
    public static String parseTodo(String[] keywords) throws DukeException {
        if (keywords.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            keywords[0] = "";
            return String.join(" ", keywords).strip();
        }
    }

    /**
     * Parses commands that have both a description and time.
     *
     * @param keywords String array consisting of "deadline" or "event" as entry 0 and
     *                 the rest of the message for the rest of the array with a separator
     *                 for the time and date.
     * @param dateTimeType Either "deadline" or "event" to determine the separator to look out for.
     * @return A string array with the description of the task in entry 0 and the time in entry 1.
     * @throws DukeException Throws if either the description is empty or there is no separator.
     */
    public static String[] parseTaskTime(String[] keywords, String dateTimeType) throws DukeException {
        if (keywords.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a " + dateTimeType + " cannot be empty.");
        } else {
            String temp = "";
            String date = "";
            boolean flag = false;
            switch (dateTimeType) {
            case "deadline":
                for (int i = 1; i < keywords.length; i++) {
                    if (flag) {
                        date = date + " " + keywords[i];
                    } else if (keywords[i].equals("/by")) {
                        flag = true;
                    } else {
                        temp = temp + " " + keywords[i];
                    }
                }
                break;

            case "event":
                for (int i = 1; i < keywords.length; i++) {
                    if (flag) {
                        date = date + " " + keywords[i];
                    } else if (keywords[i].equals("/at")) {
                        flag = true;
                    } else {
                        temp = temp + " " + keywords[i];
                    }
                }
                break;

            default:
                break;
            }
            if (date.equals("")) {
                switch (dateTimeType) {
                case "deadline":
                    throw new DukeException("☹ OOPS!!! Your deadline does not have a /by.");

                case "event":
                    throw new DukeException("☹ OOPS!!! Your event does not have an /at.");

                default:
                    break;
                }
            }
            return new String[] {temp.strip(), date.strip()};
        }
    }
}
