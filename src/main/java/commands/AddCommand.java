package commands;

import java.time.LocalDateTime;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

import tasks.Task;
import tasks.Event;
import tasks.Deadline;
import tasks.ToDo;

import exceptions.DukeException;

/**
 * AddCommand is a class dealing with commands that
 * add task items to the list of tasks. These items
 * can be ToDo, Event or Deadline tasks.
 */
public class AddCommand extends UndoableCommand {

    /**
     * Constructor for AddCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public AddCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Adds the corresponding Task type to the list of tasks
     * based on the user input. These Tasks can be either
     * a ToDo, Event or Deadline Task.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @throws DukeException  If there is invalid input.
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskLst = tasks.getTaskLst();
        if (commandArr[0].equals("todo")) {
            if (commandArr.length <= 1) {
                // the user did not provide a description for the task
                throw new DukeException(ui.getEmptyDescriptionMsg());
            }
            // Add a ToDo task
            taskLst.add(new ToDo(commandArr[1], false));
        } else if (commandArr[0].equals("deadline") || commandArr[0].equals("event")) {
            if (commandArr.length <= 1) {
                // the user did not provide a description for the task
                throw new DukeException(ui.getEmptyDescriptionMsg());
            }
            String[] fullCommand;
            if (commandArr[0].equals("deadline")) {
                if (commandArr[1].contains("/by ")) {
                    fullCommand = commandArr[1].split(" /by ");
                } else {
                    // if command is of invalid format: does not contain " /by "
                    throw new DukeException(ui.getInvalidDeadlineCmdMsg());
                }
            } else { // if command equals "event"
                if (commandArr[1].contains("/at ")) {
                    fullCommand = commandArr[1].split(" /at ");
                } else {
                    // if command is of invalid format: does not contain " /at "
                    throw new DukeException(ui.getInvalidEventCmdMsg());
                }
            }
            if (fullCommand.length <= 1) {
                // Either the description or the date-time is not provided by user
                throw new DukeException(ui.getMissingArgumentsMsg(commandArr[0]));
            }
            String description = fullCommand[0];
            String userDateTimeString = fullCommand[1];
            try {
                LocalDateTime localDateTime = Parser.parseDateTime(userDateTimeString);
                if (commandArr[0].equals("deadline")) {
                    taskLst.add(new Deadline(description, localDateTime, false));
                } else { // if command equals "event"
                    taskLst.add(new Event(description, localDateTime, false));
                }
            } catch (DateTimeParseException e) {
                // The user specified an invalid date-time format
                throw new DukeException(ui.getInvalidDateTimeFormatMsg());
            }
        } else {
            // Invalid command being supplied by the user
            throw new DukeException(ui.getInvalidCommandMsg());
        }
        // Add the UndoableCommand to the stack of commands that can be undone
        // since it is a valid command and has not thrown any errors
        super.execute(tasks, ui, storage);
        // Since the user has made changes to the code,
        // clear the redoStack of all undoable commands
        RedoCommand.redoStack.removeAllElements();
        return ui.getSuccessfulAddMsg(taskLst);
    }

    /**
     * Removes the added task from the list.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     */
    public void executeInverse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskLst = tasks.getTaskLst();
        // Remove the most recent addition to the task list
        taskLst.remove(taskLst.size() - 1);
    }

}
