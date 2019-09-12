import javafx.util.Pair; 

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Parse Manager, which parses the inputs given by the user, so our other classes
 * are able to read it with ease.
 */
class Parser {
    /**
     * Returns a Command, and each command created is of a different class from the user's input.
     * 
     * <p>There are 2 types of commands, 1-word String command, and 2-or-more-word command.
     * E.g. of 1-word String: list, bye, help.
     * E.g. of 2-or-more-word String, todo, deadline, event, done, delete.
     * The user's input is split into 2 parts, and if it is only 1-word String, 
     * If the user inputs something that is not of the correct Input type, a DukeException is thrown.
     * 
     * @param input String inputted by the user to be parsed into a Command
     * @return A Command that is executed by DukeManager
     * @throws DukeException When the user Inputs something unreadable by the program.
     * @see Command#execute(Ui, TaskList, Storage)
     */
    public Command parseToCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        Action action = getAction(inputArr[0]);
        assert inputArr.length == 1 || inputArr.length == 2 : "inputArr length is wrong length"; 

        if (inputArr.length == 1) {
            switch (action) {
            case LIST :
                return new ListCommand();   
            case EXIT :
                return new ExitCommand();      
            case HELP :
                return new HelpCommand();      
            default :
                throwInputError(action);
                return null;
            }
        } else if (inputArr.length == 2) {
            switch (action) {
            case TODO :
                return new AddCommand(new Todo(inputArr[1]));
            case DEADLINE :
                return new AddCommand(createTimedTask(inputArr[1], action));
            case EVENT :
                return new AddCommand(createTimedTask(inputArr[1], action));
            case DONE :
                return new DoneCommand(parseToNumber(inputArr[1], "Done"));
            case DELETE :
                return new DeleteCommand(parseToNumber(inputArr[1], "Delete"));
            case FIND :
                return new FindCommand(inputArr[1]);
            default :
                throw new DukeException("Oof. I apologize, but I do not understand.");
            }
        } else {
            // Not suppose to happen
            throw new DukeException("Oof. I apologize, but I do not understand.");
        }
    } 

    /**
     * Returns an Action enum from a single word String.
     * 
     * @param action The first word that is input by the user
     * @return An Action enum
     * @throws DukeException When the single word String is not of the stipulated cases
     */
    private Action getAction(String action) throws DukeException {
        assert !action.equals("") : "Empty String was obtained";
        switch (action.toLowerCase()) {
        case "list" :
            return Action.LIST;
        case "bye" :
            return Action.EXIT;
        case "help" :
            return Action.HELP;
        case "todo" :
            return Action.TODO;
        case "deadline" :
            return Action.DEADLINE;
        case "event" :
            return Action.EVENT;
        case "done" :
            return Action.DONE;
        case "delete" :
            return Action.DELETE;
        case "find" :
            return Action.FIND;
        default :
            throw new DukeException("Oof. I apologize, but no such command exists.");
        }
    }
    
    /**
     * Returns the parsed task number given by the user used for some commands E.g. Done and Delete.
     * 
     * @param taskNumber The number given by the user that is parsed into the respective task.
     * @param action The first word that is input by the user.
     * @return An Integer that is, taskNumber that is pased.
     * @throws DukeException When there is no number behind the action.
     */
    private Integer parseToNumber(String taskNumber, String action) throws DukeException {
        try {
            return Integer.parseInt(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Oof. " + action + " requires a number behind.");
        }
    }

    /**
     * Returns a Task that is time-limited e.g. Deadline, Event.
     * 
     * <p>Returns by splitting the taskString by a indicator and parsing it.
     * @param taskString The String given by the user without the Action/Command.
     * @param action The given Action by the user to decipher which task to return.
     * @return a Time-limited Task.
     * @throws DukeException When the format of the input is wrong.
     */
    private Task createTimedTask(String taskString, Action action) throws DukeException {
        String[] taskArr = null;
        switch (action) {
        case DEADLINE :
            taskArr = taskString.split(" /by ", 2);
            break;
        case EVENT :
            taskArr = taskString.split(" /at ", 2);
            break;
        default :
            throw new DukeException("Creating a time-limited task when not supposed to.");
        }

        if (taskArr.length == 2) {
            return checkTimedTask(taskArr, action);
        } else {
            throw new DukeException("Oof. There seems to be an error with your format.\n" 
            + "Please type \'help\' for more information.");
        }
    }

    /**
     * Returns a Task that is time-limited e.g. Deadline, Event.
     * 
     * <p>Used to check against the action and return the task accordingly
     * 
     * @param taskArr The task containing the task itself, and the date
     * @param action The given Action by the user to decipher which task to return.
     * @return A time-limited Task.
     * @throws DukeException When creating a time-limited task when not supposed to.
     */
    private Task checkTimedTask(String[] taskArr, Action action) throws DukeException {
        Pair<LocalDate, LocalTime> pair = obtainDateTime(taskArr[1]);
        switch (action) {
        case DEADLINE :
            return new Deadline(taskArr[0], pair.getKey(), pair.getValue());
        case EVENT :
            return new Event(taskArr[0], pair.getKey(), pair.getValue());
        default :
            throw new DukeException("Oof. Creating a time-limited task when not supposed to.");
        }
    }

    /**
     * Returns a Pair which contains the Date and Time, either can be null, not both.
     * 
     * <p>Parses the date and time and converts them into a pair to be returned.
     * 
     * @param dateTime The time and date to be parsed.
     * @return A Pair which contains the Date and Time, either can be null, not both.
     * @throws DukeException When there is an error parsing the date, time or both.
     */
    private Pair<LocalDate, LocalTime> obtainDateTime(String dateTime) throws DukeException {
        String[] dateTimeArr = dateTime.split(" ", 2);
        LocalDate date = null;
        LocalTime time = null;
        assert dateTimeArr.length == 1 || dateTimeArr.length == 2 : "dateTimeArr is wrong length.";

        if (dateTimeArr.length == 2) {
            try {
                // Parse both date and time
                date = LocalDate.parse(dateTimeArr[0], Task.DATE_FORMATTER);
                time = LocalTime.parse(dateTimeArr[1], Task.TIME_FORMATTER);
            } catch (Exception error) {
                throw new DukeException("Oof. Unable to parse both time and date.\n" 
                        + "Please use \'help\' for the formatting of time and date");
            }
        } else if (dateTimeArr.length == 1) {
            try {
                // Parse date only
                date = LocalDate.parse(dateTimeArr[0], Task.DATE_FORMATTER);
            } catch (Exception error) {
                try {
                    // Parse time only
                    time = LocalTime.parse(dateTimeArr[0], Task.TIME_FORMATTER);
                } catch (Exception errorAgain) {
                    throw new DukeException("Oof. Unable to parse both time and date.\n" 
                        + "Please use \'help\' for the formatting of time and date");
                }
            }
        } else {
            throw new DukeException("Oof. There seems to be a formatting issue.\n" 
                    + "Please use \'help\' for the formatting of time and date");
        }

        return new Pair<LocalDate,LocalTime>(date, time);
    }

    /**
     * Throws an Input Exception when a 2-or-more-Words String has only the word itself.
     * 
     * @param action The input Command/Action
     * @throws DukeException As the description of a 2-or-more-Words String is only 1-word.
     */
    private void throwInputError(Action action) throws DukeException {
        switch (action) {
        case TODO :
            throw new DukeException("Oof. The description of a todo cannot be empty.");
        case DEADLINE :
            throw new DukeException("Oof. The description of a deadline cannot be empty.");
        case EVENT :
            throw new DukeException("Oof. The description of a event cannot be empty."); 
        case DONE :
            throw new DukeException("Oof. The description of a done cannot be empty.");
        case DELETE :
            throw new DukeException("Oof. The description of a delete cannot be empty.");
        default :
            throw new DukeException("Oof. I apologize but I don't understand.");
        }
    }

}