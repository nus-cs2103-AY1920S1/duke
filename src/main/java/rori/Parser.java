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
     * If the user inputs something that is not of the correct Input type, a RoriException is thrown.
     * 
     * @param input String inputted by the user to be parsed into a Command
     * @return A Command that is executed by RoriManager
     * @throws RoriException When the user Inputs something unreadable by the program.
     * @see Command#execute(Ui, TaskList, Storage)
     */
    public Command parseToCommand(String input) throws RoriException {
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
            case TUTORIAL: 
                return createTutorialCommand();
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
            case HELP :
                return new HelpCommand(getAction(inputArr[1]));
            default :
                throw new RoriException("Oi, Baka. What is that supposed to mean?"
                        + "Type \'help\' you baka.");
            }
        } else {
            // Not suppose to happen
            throw new RoriException("Oi, Baka. What is that supposed to mean."
                    + "Type \'help\' you baka.");
        }
    } 

    /**
     * Returns an Action enum from a single word String.
     * 
     * @param action The first word that is input by the user
     * @return An Action enum
     * @throws RoriException When the single word String is not of the stipulated cases
     */
    private Action getAction(String action) throws RoriException {
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
        case "tutorial" :
            return Action.TUTORIAL;
        default :
            throw new RoriException("No such command exist you baka!");
        }
    }
    
    /**
     * Returns the parsed task number given by the user used for some commands E.g. Done and Delete.
     * 
     * @param taskNumber The number given by the user that is parsed into the respective task.
     * @param action The first word that is input by the user.
     * @return An Integer that is, taskNumber that is pased.
     * @throws RoriException When there is no number behind the action.
     */
    private Integer parseToNumber(String taskNumber, String action) throws RoriException {
        try {
            return Integer.parseInt(taskNumber);
        } catch (Exception e) {
            throw new RoriException(action + " requires a number behind you dum dum.");
        }
    }

    /**
     * Returns a Task that is time-limited e.g. Deadline, Event.
     * 
     * <p>Returns by splitting the taskString by a indicator and parsing it.
     * @param taskString The String given by the user without the Action/Command.
     * @param action The given Action by the user to decipher which task to return.
     * @return a Time-limited Task.
     * @throws RoriException When the format of the input is wrong.
     */
    private Task createTimedTask(String taskString, Action action) throws RoriException {
        String[] taskArr = null;
        switch (action) {
        case DEADLINE :
            taskArr = taskString.split(" /by ", 2);
            break;
        case EVENT :
            taskArr = taskString.split(" /at ", 2);
            break;
        default :
            assert false : "Not suppose to reach here";
            throw new RoriException("Why did I create a time-limited task?");
        }

        if (taskArr.length == 2) {
            return checkTimedTask(taskArr, action);
        } else {
            throw new RoriException("Are you baka? Why is your format all wrong!.\n" 
                    + "Type \'help\' for formatting you baka.");
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
     * @throws RoriException When creating a time-limited task when not supposed to.
     */
    private Task checkTimedTask(String[] taskArr, Action action) throws RoriException {
        Pair<LocalDate, LocalTime> pair = obtainDateTime(taskArr[1]);
        switch (action) {
        case DEADLINE :
            return new Deadline(taskArr[0], pair.getKey(), pair.getValue());
        case EVENT :
            return new Event(taskArr[0], pair.getKey(), pair.getValue());
        default :
            assert false : "Not supposed to reach here";
            throw new RoriException("Why did I create a time-limited task?");
        }
    }

    /**
     * Returns a Pair which contains the Date and Time, either can be null, not both.
     * 
     * <p>Parses the date and time and converts them into a pair to be returned.
     * 
     * @param dateTime The time and date to be parsed.
     * @return A Pair which contains the Date and Time, either can be null, not both.
     * @throws RoriException When there is an error parsing the date, time or both.
     */
    private Pair<LocalDate, LocalTime> obtainDateTime(String dateTime) throws RoriException {
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
                System.out.println(dateTimeArr[0]);
                System.out.println(dateTimeArr[1]);
                throw new RoriException("!!? You think I can parse this nonsense?\n" 
                        + "Type \'help\' for formatting you baka");
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
                    throw new RoriException("!!? You think I can parse this nonsense?\n" 
                            + "Type \'help\' for formatting you baka");
                }
            }
        } else {
            throw new RoriException("!!? You think I can parse this nonsense?\n" 
                    + "Type \'help\' for formatting you baka");
        }

        return new Pair<LocalDate,LocalTime>(date, time);
    }

    /**
     * Returns a tutorial command containing templates for todo, deadline and event.
     * @return a tutorial command
     * @throws RoriException When gettign error creating the tasks.
     */
    private TutorialCommand createTutorialCommand() throws RoriException {
        Command addTodo = parseToCommand("todo This is a Todo.");
        Command addDeadline = parseToCommand("deadline This is a Deadline. /by 12/09/2019 10:10");
        Command addEvent = parseToCommand("event This is an Event. /at 12/09/2019");
        return new TutorialCommand(addTodo, addDeadline, addEvent);
    }

    /**
     * Returns a String containing the response of rori for whether the user wants a tutorial.
     * @param input The user's input
     * @return a String of rori's response.
     */
    public String parseTutorialResponse(String input, Rori rori, Ui uiManager) throws RoriException {
        if (input.toLowerCase().equals("yes")) {
            return rori.runRori("tutorial");
        } else if (input.toLowerCase().equals("no")) {
            return uiManager.printNoTutorial();
        } else {
            throw new RoriException("Oi, Baka. What is that supposed to mean"
                    + "Type \'help\' you baka.");
        }
    }

    /**
     * Throws an Input Exception when a 2-or-more-Words String has only the word itself.
     * 
     * @param action The input Command/Action
     * @throws RoriException As the description of a 2-or-more-Words String is only 1-word.
     */
    private void throwInputError(Action action) throws RoriException {
        switch (action) {
        case TODO :
            throw new RoriException("O-o-oi. The description of a \'todo\' cannot be empty you baka!");
        case DEADLINE :
            throw new RoriException("O-o-oi. The description of a \'deadline\' cannot be empty you baka!");
        case EVENT :
            throw new RoriException("O-o-oi. The description of an \'event\' cannot be empty you baka!"); 
        case DONE :
            throw new RoriException("O-o-oi. You know that \'done\' needs a number behind, right?");
        case DELETE :
            throw new RoriException("O-o-oi. You know that \'delete\' needs a number behind, right?");
        default :
            throw new RoriException("Oi, Baka. What is that supposed to mean"
                    + "Type \'help\' you baka.");
        }
    }

}