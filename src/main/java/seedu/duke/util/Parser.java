package seedu.duke.util;

import seedu.duke.commands.*;
import seedu.duke.exceptions.DukeException;

public class Parser {

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
                String temp = parseTask(keywords, "todo");
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
            } else if (keywords[0].equals("find")) {
                String temp = parseTask(keywords, "find");
                return new FindCommand(temp);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("☹ OOPS!!! I can't mark or delete without an entry :-(");
        } catch (NumberFormatException ex) {
            throw new DukeException("☹ OOPS!!! That's not a valid entry :-(");
        }
    }

    public static String parseTask(String[] keywords, String taskType) throws DukeException {
        if (keywords.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
        } else {
            keywords[0] = "";
            return String.join(" ", keywords).strip();
        }
    }

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
