package parser;

import commands.Command;
import commands.PrintCommand;
import commands.CommandManager;
import commands.AddCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.UndoableCommand;
import java.io.IOException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;
import util.TaskList;
import storage.Storage;
import ui.Ui;


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
        BYE, DEADLINE, DELETE, DONE, EVENT, FIND, LIST, TODO, UNDO
    }

    /** Command manager. */
    private static CommandManager manager = new CommandManager();

    /** Boolean to indicate whether it is time to exit Duke.*/
    public static boolean isItTimeToExit = false;

    /**
     * Parses user input into a command executed by Duke.
     *
     * @param fullCommand the string entered by the user as a command
     * @return a Duke-executable command
     * @throws IllegalArgumentException if command not supported by duke
     * @throws IndexOutOfBoundsException if incomplete commands
     * @throws ParseException if incorrect date/time format
     */
    public static String parse(String fullCommand, TaskList tasks, Ui ui, Storage storage)
            throws IllegalArgumentException, IndexOutOfBoundsException,
            ParseException, IOException {

        // try to create the command based on the imperative and run it
        // if the imperative entered is unrecognised, inform the user

        // extract the imperative specified by the user to identify which
        // command is to be executed by ui.Duke
        String[] splitCommand = fullCommand.split(" ", 2);
        String imperative = splitCommand[0].toUpperCase();

        switch (Imperative.valueOf(imperative)) {
        case BYE:
            isItTimeToExit = true;
            return parseByeCommand(imperative, tasks, ui, storage);
        case DELETE:
            return parseDeleteCommand(imperative, splitCommand[1], tasks, ui, storage);
        case DONE:
            return parseDoneCommand(imperative, splitCommand[1], tasks, ui, storage);
        case LIST:
            return parsePrintCommand(imperative, tasks, ui, storage);
        case TODO:
            Todo todoForAddCommand = new Todo(splitCommand[1],false);
            return parseAddCommand(imperative, todoForAddCommand, tasks, ui, storage);
        case DEADLINE:
            String newDeadlineDescription = splitTaskAttributes(splitCommand[1], "\\/")[0];
            String newDeadlineDueDate = splitTaskAttributes(splitCommand[1], "\\/")[1];
            Deadline deadlineForAddCommand = new Deadline(newDeadlineDescription,
                    newDeadlineDueDate, false);
            return parseAddCommand(imperative, deadlineForAddCommand, tasks, ui, storage);
        case EVENT:
            String newEventDescription = splitTaskAttributes(splitCommand[1], "\\/")[0];
            String newEventDate = splitTaskAttributes(splitCommand[1], "\\/")[1];
            Event eventForAddCommand = new Event(newEventDescription,
                    newEventDate, false);
            return parseAddCommand(imperative, eventForAddCommand, tasks, ui, storage);
        case FIND:
            return parseFindCommand(imperative, splitCommand[1], tasks, ui, storage);
        case UNDO:
            return parseUndoCommand(tasks, ui, storage);
        default:
            throw new IllegalArgumentException();
        }
    }

    private static String parseByeCommand(String imperative, TaskList tasks, Ui ui,
                                          Storage storage) throws IOException {
        ExitCommand exitCommand  = new ExitCommand(imperative);
        String responseString = manager.executeCommand(exitCommand, tasks, ui, storage);
        return responseString;
    }

    private static String parseDeleteCommand(String imperative, String index, TaskList tasks,
                                             Ui ui, Storage storage) throws IOException {
        UndoableCommand deleteCommand  = new DeleteCommand(imperative, index);
        String responseString = manager.executeCommand(deleteCommand, tasks, ui, storage);
        return responseString;
    }

    private static String parseDoneCommand(String imperative, String index, TaskList tasks,
                                             Ui ui, Storage storage) throws IOException {
        UndoableCommand doneCommand  = new DoneCommand(imperative, index);
        String responseString = manager.executeCommand(doneCommand, tasks, ui, storage);
        return responseString;
    }

    private static String parsePrintCommand(String imperative, TaskList tasks, Ui ui,
                                          Storage storage) throws IOException {
        PrintCommand printCommand  = new PrintCommand(imperative);
        String responseString = manager.executeCommand(printCommand, tasks, ui, storage);
        return responseString;
    }

    private static String parseAddCommand(String imperative, Task task, TaskList tasks,
                                           Ui ui, Storage storage) throws IOException {
        UndoableCommand addCommand  = new AddCommand(imperative, task);
        String responseString = manager.executeCommand(addCommand, tasks, ui, storage);
        return responseString;
    }

    private static String parseFindCommand(String imperative, String searchTerm, TaskList tasks,
                                           Ui ui, Storage storage) throws IOException {
        Command findCommand  = new FindCommand(imperative, searchTerm);
        String responseString = manager.executeCommand(findCommand, tasks, ui, storage);
        return responseString;
    }

    private static String parseUndoCommand(TaskList tasks, Ui ui,
                                           Storage storage) throws IOException {
        String responseString = manager.undoCommand(tasks, ui, storage);
        return responseString;
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
        String[] taskAttributes = command.split(regex, 2);
        String taskDescription = taskAttributes[0];
        String taskDateTime = taskAttributes[1].split(" ", 2)[1];
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
            assert emptyList.isEmpty();
            System.out.println("list remains empty.");
        }
    }
}
