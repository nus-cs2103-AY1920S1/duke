package parser;

import commands.PrintCommand;
import commands.Command;
import commands.AddCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.FindCommand;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import util.TaskList;

import java.text.ParseException;

/**
 * Encapsulates a handler that tries to make sense of user commands.
 *
 *  @author atharvjoshi
 *  @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class Parser {
    /**
     * Enumeration of all command types supported by Duke.
     */
    private enum Imperative {
        BYE, DEADLINE, DELETE, DONE, EVENT, FIND, LIST, TODO
    }

    /**
     * Parses user input into a command executed by Duke.
     *
     * @param fullCommand the string entered by the user as a command
     * @return a Duke-executable command
     * @throws IllegalArgumentException if command not supported by duke
     * @throws IndexOutOfBoundsException if incomplete commands
     * @throws ParseException if incorrect date/time format
     */
    public static Command parse(String fullCommand)
            throws IllegalArgumentException, IndexOutOfBoundsException,
            ParseException {

        // try to create the command based on the imperative and run it
        // if the imperative entered is unrecognised, inform the user

        // extract the imperative specified by the user to identify which
        // command is to be executed by ui.Duke
        String[] splitCommand = fullCommand.split(" ", 2);
        String imperative = splitCommand[0].toUpperCase();

        switch (Imperative.valueOf(imperative)) {
        case BYE:
            return new ExitCommand(imperative);
        case DELETE:
            return new DeleteCommand(imperative, splitCommand[1]);
        case DONE:
            return new DoneCommand(imperative, splitCommand[1]);
        case LIST:
            return new PrintCommand(imperative);
        case TODO:
            return new AddCommand(imperative, new Todo(splitCommand[1],
                    false));
        case DEADLINE:
            return new AddCommand(imperative, new Deadline(
                    splitTaskAttributes(splitCommand[1], "\\/")[0],
                    splitTaskAttributes(splitCommand[1], "\\/")[1],
                    false));
        case EVENT:
            return new AddCommand(imperative, new Event(
                    splitTaskAttributes(splitCommand[1], "\\/")[0],
                    splitTaskAttributes(splitCommand[1], "\\/")[1],
                    false));
        case FIND:
            return new FindCommand(imperative, splitCommand[1]);
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the string containing date/time information for deadline and
     * event type tasks.
     *
     * @param command string containing task attribute information
     * @param regex the delimiter used to split the command string
     * @return string containing date/time information
     */
    private static String[] splitTaskAttributes(String command, String regex) {
        // try to obtain the description and date/time information of
        // the deadline / event. Inform user if the input is in an
        // incorrect format.
        String[] splitTaskAttributes = command.split(regex, 2);
        String taskDescription = splitTaskAttributes[0];
        String taskDateTime = splitTaskAttributes[1].split(" ", 2)[1];
        return new String[] {taskDescription, taskDateTime};
    }

    /**
     * Reads text file containing saved tasks, converts them into tasks.Task objects,
     * and adds to the task list in order to load the last saved task list.
     *
     * @param line string containing one line from the text file
     * @param emptyList empty list to add the parsed tasks to
     * @throws ParseException if incorrect date/time format
     */
    public static void parseTextToTask(String line, TaskList emptyList) throws
            ParseException {
        String[] splitLine = line.split("-", 3);
        boolean isTaskDone = splitLine[1].equals("Y");

        switch (splitLine[0]) {
        case "D":
            emptyList.add(new Deadline(
                    splitTaskAttributes(splitLine[2], "-")[0],
                    splitTaskAttributes(splitLine[2], "-")[1], isTaskDone));
            break;
        case "E":
            emptyList.add(new Event(
                    splitTaskAttributes(splitLine[2], "-")[0],
                    splitTaskAttributes(splitLine[2], "-")[1], isTaskDone));
            break;
        case "T":
            emptyList.add(new Todo(splitLine[2], isTaskDone));
            break;
        default:
            System.out.println("list remains empty.");
        }
    }
}
