package main;

import command.*;
import exception.DeleteException;
import exception.DoneException;
import exception.DukeException;
import exception.TaskException;

/**
 * Parses all the commands input by the user.
 *
 * The only public method, parse(), is used to determine which command will be returned and executed in Duke. The other
 * two methods are private and used to assist the parse() method in processing an Event or a Deadline type Command.
 */
public class Parser {
    public Parser() { }

    /**
     * Returns a String array which has two elements.
     *
     * The first element is the name of the new Event Task and the second element is the String representing the date
     * of the Event Task.
     *
     * @param command A string array representing the initial input split into individual words.
     * @return Returns a String array which contains the name and the date of a new Event Task.
     * @throws TaskException Thrown when there is insufficient information to create an Event Task.
     */
    private String[] getEventDetails(String[] command) throws TaskException {
        if (command.length <= 1) {
            throw new TaskException();
        }
        String toReturn = "";
        for(int i = 1; i < command.length; i++) {
            toReturn += command[i] + " ";
        }

        return toReturn.split(" /at ");
    }

    /**
     * Returns a String array which has two elements.
     *
     * The first element is the name of the new Deadline Task and the second element is the String representing the
     * date of the Deadline Task.
     *
     * @param command A string array representing the initial input split into individual words.
     * @return Returns a String array which contains the name and the date of a new Deadline Task.
     * @throws TaskException Thrown when there is insufficient information to create an Deadline Task.
     */
    private String[] getDeadlineDetails(String[] command) throws TaskException {
        if (command.length <= 1) {
            throw new TaskException();
        }
        String toReturn = "";
        for(int i = 1; i < command.length; i++) {
            toReturn += command[i] + " ";
        }

        return toReturn.split(" /by ");
    }


    /**
     * Parses the input and returns a Command that corresponds to the type of input given.
     *
     * When a String (commandLine) is supplied, this method will split commandLine into multiple words (via
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
            try {
                if (nextCommand.length <= 1) {
                    throw new DoneException();
                } else {
                    return new DoneCommand(Integer.valueOf(nextCommand[1]) - 1);
                }
            } catch (DukeException dE) {
                System.err.println(dE);
            }
            break;

        case "todo":
            try {
                if (nextCommand.length <= 1) {
                    throw new TaskException();
                }
                String todoName = "";
                for (int i = 1; i < nextCommand.length; i++) {
                    todoName += nextCommand[i] + " ";
                }

                return new TodoCommand(todoName);
            } catch (TaskException toEx) {
                System.err.println(toEx);
            }
            break;
        case "event":
            try {
                String[] eventDetails = getEventDetails(nextCommand);

                return new EventCommand(eventDetails[0], eventDetails[1]);

            } catch (TaskException evEx) {
                System.err.println(evEx);
            }
            break;

        case "deadline":
            try {
                String[] deadlineDetails = getDeadlineDetails(nextCommand);

                return new DeadlineCommand(deadlineDetails[0], deadlineDetails[1]);

            } catch (TaskException evEx) {
                System.err.println(evEx);
            }
            break;

        case "delete":
            try {
                if (nextCommand.length <= 1) {
                    throw new DeleteException();
                } else {
                    int index = Integer.parseInt(nextCommand[1]);

                    return new DeleteCommand(index);
                }
            } catch (DeleteException deEx) {
                System.err.println(deEx);
            }
            break;


        default:
            break;
        }

        return new UnknownCommand();
    }
}

