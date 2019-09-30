package main;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.TodoCommand;
import command.UnknownCommand;
import exception.TaskException;

/**
 * Parses all the commands input by the user.
 *
 * <p></p>The only public method, parse(), is used to determine which command will be returned and executed in Duke.
 * The other two methods are private and used to assist the parse() method in processing an Event or a Deadline type
 * Command.
 */
public class Parser {
    public Parser() {

    }

    /**
     * Returns a String array which has two elements.
     *
     * <p></p>The first element is the name of the new Event Task and the second element is the String representing the
     * date of the Event Task.
     *
     * @param command A string array representing the initial input split into individual words.
     * @return Returns a String array which contains the name and the date of a new Event Task.
     * @throws TaskException Thrown when there is insufficient information to create an Event Task.
     */
    private String[] getEventDetails(String[] command) {
        taskHasName(command);
        String toReturn = "";
        for (int i = 1; i < command.length; i++) {
            toReturn += command[i] + " ";
        }

        return toReturn.split(" /at ");
    }

    /**
     * Returns a String array which has two elements.
     *
     * <p></p>The first element is the name of the new Deadline Task and the second element is the String representing
     * the date of the Deadline Task.
     *
     * @param command A string array representing the initial input split into individual words.
     * @return Returns a String array which contains the name and the date of a new Deadline Task.
     * @throws TaskException Thrown when there is insufficient information to create an Deadline Task.
     */
    private String[] getDeadlineDetails(String[] command) {
        taskHasName(command);
        String toReturn = "";
        for (int i = 1; i < command.length; i++) {
            toReturn += command[i] + " ";
        }

        return toReturn.split(" /by ");
    }

    private void taskHasName(String[] nextCommand) {
        assert nextCommand.length > 1 : "Task has no name";
    }

    private void commandHasTask(String[] nextCommand) {
        assert nextCommand.length > 1 : "Command has no Task";
    }


    /**
     * Parses the input and returns a Command that corresponds to the type of input given.
     *
     * <p></p>When a String (commandLine) is supplied, this method will split commandLine into multiple words (via
     * String.split(" "). This will result in a String array containing individual words. The first word is then used
     * to determine which Command is to be returned. If an invalid command is given, an UnknownCommand will be returned.
     *
     * @param commandLine A string representing a full line of input from the user.
     * @return Returns a Command object corresponding to the type of input from the user.
     */
    public Command parse(String commandLine) {
        String[] nextCommand = commandLine.split(" ");
        switch (nextCommand[0]) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "done":
            commandHasTask(nextCommand);
            return new DoneCommand(Integer.valueOf(nextCommand[1]) - 1);

        case "todo":
            taskHasName(nextCommand);
            String todoName = "";
            for (int i = 1; i < nextCommand.length; i++) {
                todoName += nextCommand[i] + " ";
            }
            return new TodoCommand(todoName);

        case "event":
            String[] eventDetails = getEventDetails(nextCommand);
            return new EventCommand(eventDetails[0], eventDetails[1]);


        case "deadline":
            String[] deadlineDetails = getDeadlineDetails(nextCommand);
            return new DeadlineCommand(deadlineDetails[0], deadlineDetails[1]);

        case "delete":
            commandHasTask(nextCommand);
            int index = Integer.parseInt(nextCommand[1]);
            return new DeleteCommand(index);

        case "find":
            commandHasTask(nextCommand);
            return new FindCommand(nextCommand[1]);

        default:
            break;
        }
        return new UnknownCommand();
    }
}

