package seedu.duke.util;

import seedu.duke.commands.MasterCommand;
import seedu.duke.commands.base.*;
import seedu.duke.commands.trivia.*;
import seedu.duke.commands.base.TriviaStartCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.TriviaException;

public class Parser {

    private static String mode = "base";

    public static String getMode() {
        return mode;
    }

    public static MasterCommand parse(String input) throws DukeException, TriviaException {
        if (mode.equals("trivia")){
            return parseTrivia(input);
        } else {
            return parseBase(input);
        }
    }

    public static TriviaCommand parseTrivia(String input) throws TriviaException {
        String[] keywords = input.split(" ");
        try {
            if (keywords[0].equals("add")) {
                return new TriviaAddQuestionCommand(parseAddQuestion(keywords));
            } else if (keywords[0].equals("addans")) {
                String[] args = parseAddAnswer(keywords);
                return new TriviaAddAnswerCommand(args[0], Integer.parseInt(args[1]));
            } else if (keywords[0].equals("view-all")) {
                return new TriviaViewAllCommand();
            } else if (keywords[0].equals("exit")) {
                mode = "base";
                return new TriviaExitCommand();
            } else {
                throw new TriviaException("FOOL!!! I can't hear you over THE WORLD!!!");
            }
        } catch (NumberFormatException ex) {
            throw new TriviaException("FOOL!!! I only accept numbers!");
        }
    }

    /**
     * Parses commands from the user in base mode. Transfers the line into logic that will be executed by the commands.
     *
     * @param input String input from the user.
     * @return Command corresponding to the first word in user input.
     * @throws DukeException Throws if user does not input any existing keyword.
     */
    public static Command parseBase(String input) throws DukeException {
        String[] keywords = input.split(" ");
        try {
            if (keywords[0].equals("bye")) {
                return new ByeCommand();
            } else if (keywords[0].equals("list")) {
                return new ListCommand();
            } else if (keywords[0].equals("done")) {
                return new DoneCommand(parseIndex(keywords));
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
                return new DeleteCommand(parseIndex(keywords));
            } else if (keywords[0].equals("find")) {
                String temp = parseTask(keywords, "find");
                return new FindCommand(temp);
            } else if (keywords[0].equals("trivia")) {
                mode = "trivia";
                return new TriviaStartCommand();
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("OOPS!!! I can't mark or delete without an entry :-(");
        } catch (NumberFormatException ex) {
            throw new DukeException("OOPS!!! That's not a valid entry :-(");
        }
    }

    /**
     * Parses and task-only command to see if it has a valid input.
     *
     * @param keywords String array consisting of taskType as entry 0 and the rest of the message for the rest.
     * @return A string consisting of the original input string but without the first term.
     * @throws DukeException Throws if user did not supply the requirements for the task.
     */
    public static String parseTask(String[] keywords, String taskType) throws DukeException {
        if (keywords.length < 2) {
            throw new DukeException("OOPS!!! The description of a " + taskType + " cannot be empty.");
        } else {
            keywords[0] = "";
            return String.join(" ", keywords).strip();
        }
    }

    public static String parseAddQuestion(String[] keywords) throws TriviaException {
        if (keywords.length < 2) {
            throw new TriviaException("FOOL!!! You waste my time!");
        } else {
            keywords[0] = "";
            return String.join(" ", keywords).strip();
        }
    }

    public static String[] parseAddAnswer(String[] keywords) throws TriviaException {
        if (keywords.length < 2) {
            throw new TriviaException("FOOL!!! You waste my time!");
        } else {
            String[] result = new String[2];
            result[1] = keywords[1];
            keywords[0] = "";
            keywords[1] = "";
            result[0] = String.join(" ", keywords).strip();
            return result;
        }

    }

    /**
     * Parses commands that have index numbers as arguments
     *
     * @param keywords String array consisting of taskType as entry 0 and the index for entry 1.
     * @return Am integer that serves as the index of the task being operated on.
     * @throws DukeException Throws if user does not provide exactly 2 arguments in their command.
     */
    public static int parseIndex(String[] keywords) throws DukeException {
        if (keywords.length > 2) {
            throw new DukeException("OOPS!!! You supplied too may arguments.");
        } else if (keywords.length < 2) {
            throw new DukeException("OOPS!!! I cannot perform the task without an entry.");
        } else {
            return Integer.parseInt(keywords[1]);
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
            throw new DukeException("OOPS!!! The description of a " + dateTimeType + " cannot be empty.");
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
                    throw new DukeException("OOPS!!! Your deadline does not have a /by.");

                case "event":
                    throw new DukeException("OOPS!!! Your event does not have an /at.");

                default:
                    break;
                }
            }
            return new String[] {temp.strip(), date.strip()};
        }
    }
}
