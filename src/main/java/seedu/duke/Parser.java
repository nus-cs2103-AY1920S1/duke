package seedu.duke;

import java.text.ParseException;
import java.util.ArrayList;
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

        if (command.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        String[] detailsArray = command.split(" ");
        if (detailsArray.length == 1 && detailsArray[0].equalsIgnoreCase("sort")) {
            tasks.sortTask();
            return new ListCommand();
        } else if (command.trim().equalsIgnoreCase("show stats")) {
            return new StatisticsCommand();
        } else if (detailsArray.length == 1 && (detailsArray[0].equalsIgnoreCase("todo")
                || detailsArray[0].equalsIgnoreCase("deadline")
                || detailsArray[0].equalsIgnoreCase("event")
                || detailsArray[0].equalsIgnoreCase("done")
                || detailsArray[0].equalsIgnoreCase("delete")
                || detailsArray[0].equalsIgnoreCase("find"))) {
            throw new EmptyDescriptionException(ui.getEmptyDescriptionMsg(detailsArray[0]));
        } else {
            switch (detailsArray[0].toLowerCase()) {
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
                return new DeleteCommand(getAllIndex(command));
            case "done":
                return new DoneCommand(getAllIndex(command));
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(getAllIndex(command));
            default:
                throw new InvalidInputException(ui.getInvalidInputMsg());
            }
        }
    }

    /**
     * Obtain all valid index for the command and return them.
     * Check if all the inputs are valid. Else, throw appropriate errors.
     *
     * @param command the input command
     * @throws InvalidFindDescription if the description of find is longer than one
     * @throws InvalidIndexException if the index is invalid
     * @throws InvalidInputException if the input is invalid
     */
    private static ArrayList<Object> getAllIndex(String command) throws InvalidFindDescription,
            InvalidIndexException, InvalidInputException {
        ArrayList<Object> indexList = new ArrayList<>();
        String[] arr1 = command.split(" ", 2);    // to split the command and the index
        String[] arr2 = arr1[1].split("[,] ");

        switch (arr1[0].toLowerCase()) {
        case "find":
            for (int i = 0; i < arr2.length; i++) {
                // split the individual keyword to check if it contains multiple words
                if (arr2[i].split(" ").length >= 2) {
                    System.out.println(arr2[i]);
                    throw new InvalidFindDescription(ui.getInvalidFindMsg());
                } else {
                    indexList.add(arr2[i]);
                }
            }
            return indexList;
        case "done":
        case "delete":
            for (int i = 0; i < arr2.length; i++) {
                if (!(Integer.parseInt(arr2[i]) <= tasks.getSize()) || !(Integer.parseInt(arr2[i]) > 0)) {
                    throw new InvalidIndexException(ui.getInvalidIndexMsg(arr1[0]));
                } else {
                    indexList.add(Integer.parseInt(arr2[i]) - 1);
                }
            }
            return indexList;
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
        if (detailsArray[0].equalsIgnoreCase("deadline")) {
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
