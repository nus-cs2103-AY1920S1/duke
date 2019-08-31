package cs2103t.duke.parse;

import cs2103t.duke.command.*;
import cs2103t.duke.exception.DukeException;
import cs2103t.duke.exception.InvalidIDException;
import cs2103t.duke.exception.InvalidKeywordException;
import cs2103t.duke.task.TaskType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents parsing logic for commands. A full command will have the first word as one of the valid keywords (matching
 * a TaskType, and subsequent words should match the required format (@see Duke), or an Exception may be thrown.
 */
public class Parser {
    /** Full command. */
    private String input;

    /**
     * Constucts a parser on input.
     * @param input full command.
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * Gets the TaskType of the command.
     * @return the task type.
     * @throws DukeException if keyword is invalid.
     */
    public TaskType getInputTaskType() throws DukeException {
        String keyword = this.input.split("\\s+")[0];
        TaskType taskType = TaskType.convertToTaskType(keyword);
        if (taskType == null) {
            throw new InvalidKeywordException(keyword);
        }
        return taskType;
    }

    /**
     * Gets the String of command after the keyword.
     * Returns an empty string if full command contains <2 words.
     * @return description.
     */
    public String getInputEntireDescription() {
        Scanner sc = new Scanner(this.input);
        sc.next(); //scan past taskType
        String description = "";
        if (sc.hasNext()) {
            description = sc.nextLine().trim();
        }
        sc.close();
        return description;
    }

    /**
     * Parses fullCommand String input into a Command.
     * @param fullCommand user input.
     * @return Command corresponding to user input.
     * @throws DukeException if no valid input is given.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("")) {
            throw new DukeException("Please give me a command master! (You didn't input any command, but entered...");
        }

        Parser parser = new Parser(fullCommand);
        TaskType taskType = parser.getInputTaskType();
        String descr = parser.getInputEntireDescription();
        Command cmd;
        switch (taskType) {
        case LIST:
            cmd = new ListCommand();
            break;
        case DELETE:
            cmd = new DeleteCommand(descr);
            break;
        case DONE:
            cmd = new DoneCommand(descr);
            break;
        case EXIT:
            cmd = new ExitCommand();
            break;
        default: //add or wrong
            cmd = new AddCommand(taskType, descr);
        }

        return cmd;
    }

    /*
    public String getDescriptionWithoutDate() { //only if input is entire description alr; aka for indiv cs2103t.duke.task.Task classes
        return "";
    }
     */

    /**
     * Converts string to Date.
     * @param input string containing date.
     * @return Date object representing the date.
     * @throws DukeException if input is not in "dd/MM/yyyy HHmm" format.
     */
    public static Date convertToDate(String input) throws DukeException{
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(input);
        } catch (ParseException e) {
            throw new DukeException("Date is wrong format, try again");
        } finally {
            return date;
        }
    }

    /**
     * Converts string to int.
     * @param input string containing only the id.
     * @return id.
     * @throws DukeException if string is not in parsable format.
     */
    public static int parseStrToInt(String input) throws DukeException {
        int id = 0;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidIDException(input);
        }
        return id;
    }
}
