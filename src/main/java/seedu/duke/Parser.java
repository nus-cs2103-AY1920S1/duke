package seedu.duke;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import static seedu.duke.Task.DATE_FORMAT;

/**
 * Parser is a class that deals with making sense of the user's commands.
 */
public class Parser {

    private static int indexOfByAt = 0;
    private static Date dateTime;
    private static TaskList tasks = null;
    private static Ui ui = null;

    /**
     * Constructor of the Parser class.
     */
    public Parser() {
        indexOfByAt = 0;
        dateTime = null;
    }

    /**
     * Parses the string containing the date and time into a Date of the format "dd/MM/yyyy HHmm".
     *
     * @param str the string containing the date and time
     * @return the Date of the format "dd/MM/yyyy HHmm"
     * @throws ParseException exception thrown when the string is of a wrong format
     */
    public static Date dateParse(String str) throws ParseException {
        assert !str.equals("") : "The date cannot be an empty string";
        return DATE_FORMAT.parse(str);
    }

    /**
     * Takes in a command in the form of a string and direct it to an appropriate command.
     *
     * @param command the command that the user input
     * @param ui the Ui object being used
     * @return the Command object that the user input correspond to
     * @throws DukeException exception thrown when the timestamp or description is missing, or when input is invalid
     * @throws ParseException exception thrown when the timestamp is of incorrect format
     */
    public static Command parse(String command, Ui ui, TaskList tasks) throws DukeException, ParseException {
        Parser.tasks = tasks;
        Parser.ui = ui;
        assert !command.equals("") : "The description of the command cannot be an empty string";
        if (command.equals("bye")) {
            return new ExitCommand();
        }
        String[] detailsArray = command.split(" ");
        if (detailsArray.length == 1 && (detailsArray[0].equals("todo") || detailsArray[0].equals("deadline")
                || detailsArray[0].equals("event") || detailsArray[0].equals("done") || detailsArray[0].equals("delete")
                || detailsArray[0].equals("find"))) {
            throw new EmptyDescriptionException(ui.getEmptyDescriptionMsg(detailsArray[0]));
        } else {
            switch (detailsArray[0]) {
            case "todo":
                return new TodoCommand(String.join(" ",
                        Arrays.copyOfRange(detailsArray, 1, detailsArray.length)));
            case "deadline":
                getDate(detailsArray);
                return new DeadlineCommand(String.join(" ",
                        Arrays.copyOfRange(detailsArray, 1, indexOfByAt)), dateTime);
            case "event":
                getDate(detailsArray);
                return new EventCommand(String.join(" ",
                        Arrays.copyOfRange(detailsArray, 1, indexOfByAt)), dateTime);
            case "delete":
                checkValidDescription(detailsArray);
                return new DeleteCommand(Integer.parseInt(detailsArray[1]) - 1);
            case "done":
                checkValidDescription(detailsArray);
                return new DoneCommand(Integer.parseInt(detailsArray[1]) - 1);
            case "list":
                return new ListCommand();
            case "find":
                checkValidDescription(detailsArray);
                return new FindCommand(String.join(" ",
                        Arrays.copyOfRange(detailsArray, 1, detailsArray.length)));
            default:
                throw new InvalidInputException(ui.getInvalidInputMsg());
            }
        }
    }

    /**
     * Check if all the inputs are valid. Else, throw appropriate errors.
     *
     * @param arr the array containing the command
     * @throws InvalidFindDescription if the description of find is longer than one
     * @throws InvalidIndexException if the index is invalid
     * @throws InvalidInputException if the input is invalid
     */
    private static void checkValidDescription(String[] arr) throws InvalidFindDescription,
            InvalidIndexException, InvalidInputException {
        switch (arr[0]) {
        case "find":
            if (arr.length > 2) {
                throw new InvalidFindDescription(ui.getInvalidFindMsg());
            }
            break;
        case "done":
        case "delete":
            if (!(Integer.parseInt(arr[1]) <= tasks.getSize()) || !(Integer.parseInt(arr[1]) > 0)) {
                throw new InvalidIndexException(ui.getInvalidIndexMsg(arr[0]));
            }
            break;
        default:
            throw new InvalidInputException(ui.getInvalidInputMsg());
        }
    }


    /**
     * Updates the indexOfByAt variable and the dateTime variable according to the array.
     *
     * @param detailsArray the array containing the description of the command
     * @throws ParseException if the timestamp is of incorrect format
     * @throws MissingTimeStampException if the timestamp is missing, or "/by" or "/at" is missing
     */
    static void getDate(String[] detailsArray) throws ParseException, MissingTimeStampException {
        if (detailsArray[0].equals("deadline")) {
            for (int i = 0; i < detailsArray.length; i++) {
                if (detailsArray[i].equals("/by")) {
                    dateTime = dateParse(String.join(" ",
                            Arrays.copyOfRange(detailsArray, i + 1, detailsArray.length)));
                    indexOfByAt = i;
                    return;
                }
            }
            throw new MissingTimeStampException("☹ OOPS!!! Missing timestamp!");
        } else {
            for (int i = 0; i < detailsArray.length; i++) {
                if (detailsArray[i].equals("/at")) {
                    dateTime = dateParse(String.join(" ",
                            Arrays.copyOfRange(detailsArray, i + 1, detailsArray.length)));
                    indexOfByAt = i;
                    return;
                }
            }
            throw new MissingTimeStampException("☹ OOPS!!! Missing timestamp!");
        }
    }

    /**
     * Returns the dateTime variable of the command.
     *
     * @return the dateTime variable of the command
     */
    Date getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns the indexOfByAt variable of the command.
     *
     * @return the indexOfByAt variable of the command
     */
    int getIndexOfByAt() {
        return this.indexOfByAt;
    }

}
