package seedu.duke.logic.parser;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.commons.exceptions.commandexceptions.InvalidCommandException;
import seedu.duke.commons.exceptions.loadexceptions.FileNotFoundException;
import seedu.duke.logic.command.AboutCommand;
import seedu.duke.logic.command.AddCommand;
import seedu.duke.logic.command.ClearCacheCommand;
import seedu.duke.logic.command.Command;
import seedu.duke.logic.command.DeleteCommand;
import seedu.duke.logic.command.DoneCommand;
import seedu.duke.logic.command.ExitCommand;
import seedu.duke.logic.command.FindCommand;
import seedu.duke.logic.command.HelpCommand;
import seedu.duke.logic.command.ListCommand;
import seedu.duke.logic.command.RandomCommand;
import seedu.duke.logic.command.RedoCommand;
import seedu.duke.logic.command.SortCommand;
import seedu.duke.logic.command.UndoCommand;
import seedu.duke.logic.command.UndoneCommand;
import seedu.duke.logic.validation.DeadlineValidation;
import seedu.duke.logic.validation.EventValidation;
import seedu.duke.logic.validation.LoadValidation;
import seedu.duke.logic.validation.Validation;
import seedu.duke.model.Duke;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.task.Deadline;
import seedu.duke.model.task.Event;
import seedu.duke.model.task.TaskList;
import seedu.duke.model.task.Todo;
import seedu.duke.storage.Storage;

import java.io.File;
import java.util.Scanner;

/**
 * Defines a Parse object.
 * Contains methods to parse contents in storage files and user input.
 */
public class Parser {

    /**
     * Returns a Command object given a full line of text.
     * @param fullCommand Line of instructions presumably from user input.
     * @param duke Instance of Duke which calls this method and contains the relevant attribute for TaskList history.
     * @return Command object based on information extracted from full command given.
     * @throws DukeException If full command taken in is invalid and cannot be used to return any Command.
     */
    public static Command parse(String fullCommand, Duke duke) throws DukeException {
        Scanner sc = new Scanner(fullCommand.trim());

        if (!sc.hasNext()) {
            Validation.ensureNonEmptyCommand("");
        }
        String command = sc.next();
        if (!command.equals(ClearCacheCommand.getRequiredKey())) {
            command = command.toLowerCase();
        }
        String taskString = "";
        if (sc.hasNextLine()) {
            taskString = sc.nextLine().trim();
        }

        if (command.equals("bye")) {
            Validation.ensureEmptyTaskString(command, taskString);
            assert taskString.equals("") : "Command is empty in Parser but no exception was thrown.";
            return new ExitCommand();
        } else if (command.equals("list")) {
            Validation.ensureEmptyTaskString(command, taskString);
            assert taskString.equals("") : "Description for command not meant to have any description is non-empty "
                    + "in Parser but no exception was thrown.";
            return new ListCommand();
        } else if (command.equals("todo")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            assert !taskString.equals("") : "Task Description for a Task is empty in Parser but "
                    + "no exception was thrown.";
            return new AddCommand("todo", taskString, null);
        } else if (command.equals("deadline")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            String[] descriptionAndDateTime = DeadlineValidation.getValidatedDescriptionAndDateTime(taskString);
            taskString = descriptionAndDateTime[0];
            String dateTimeString = descriptionAndDateTime[1];

            DateAndTime dateAndTime = DeadlineValidation.getValidatedDateAndTime(dateTimeString);
            assert !taskString.equals("") : "Task Description for a Task is empty in Parser but "
                    + "no exception was thrown.";
            assert dateAndTime.getDate() != null : "Deadline has no Date in Parser but no exception was thrown.";

            return new AddCommand("deadline", taskString, dateAndTime);
        } else if (command.equals("event")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            String[] descriptionAndDateTime = EventValidation.getValidatedDescriptionAndDateTime(taskString);
            taskString = descriptionAndDateTime[0];
            String dateTimeString = descriptionAndDateTime[1];

            DateAndTime dateAndTime = EventValidation.getValidatedDateAndTime(dateTimeString);
            assert !taskString.equals("") : "Task Description is empty in Parser but no exception was thrown.";
            assert dateAndTime.getDate() != null : "Event has no Date in Parser but no exception was thrown.";
            assert dateAndTime.getTimeStart() != null : "Event has no start time in Parser but "
                    + "no exception was thrown.";
            assert dateAndTime.getTimeEnd() != null : "Event has no end time in Parser but "
                    + "no exception was thrown.";

            return new AddCommand("event", taskString, dateAndTime);
        } else if (command.equals("find")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            assert !taskString.equals("") : "Index for find command is empty in Parser but no exception was thrown.";
            return new FindCommand(taskString);
        } else if (command.equals("done")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            assert !taskString.equals("") : "Index for done command is empty in Parser but no exception was thrown.";
            return new DoneCommand(taskString);
        } else if (command.equals("undone")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            assert !taskString.equals("") : "Index for undone command is empty in Parser but no exception was thrown.";
            return new UndoneCommand(taskString);
        } else if (command.equals("delete")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            assert !taskString.equals("") : "Index for delete command is empty in Parser but no exception was thrown.";
            return new DeleteCommand(taskString);
        } else if (command.equals("sort")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            assert !taskString.equals("") : "Index for delete command is empty in Parser but no exception was thrown.";
            return new SortCommand(taskString);
        } else if (command.equals("random")) {
            Validation.ensureNonEmptyTaskString(command, taskString);
            int size = Validation.getValidatedNaturalNumber(taskString, false);
            assert !taskString.equals("") : "Index for delete command is empty in Parser but no exception was thrown.";
            return new RandomCommand(size);
        } else if (command.equals("help")) {
            return new HelpCommand(taskString);
        } else if (command.equals("undo")) {
            int numberOfTimes = taskString.toLowerCase().equals("all")
                    ? duke.getTaskListHistory().getCurrIndex()
                    : Validation.getValidatedNaturalNumber(taskString, true);
            return new UndoCommand(duke, numberOfTimes);
        } else if (command.equals("redo")) {
            int numberOfTimes = taskString.toLowerCase().equals("all")
                    ? duke.getTaskListHistory().getSize() - duke.getTaskListHistory().getCurrIndex() - 1
                    : Validation.getValidatedNaturalNumber(taskString, true);
            return new RedoCommand(duke,numberOfTimes);
        } else if (command.equals("about")) {
            return new AboutCommand();
        } else if (command.equals("clearcache")) {
            return new ClearCacheCommand(duke, "");
        } else if (ClearCacheCommand.getClearCommandRequested()) {
            return new ClearCacheCommand(duke, command);
        } else {
            Validation.ensureNonEmptyCommand(command);
            assert !command.equals("") : "Command is empty in Parser but no exception was thrown.";
            throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }

    }

    /**
     * Returns a TaskList of Tasks given a Storage containing the storage-format String representation of a TaskList.
     * Creates Tasks based on tokens from the Storage and adds them to the TaskList to be returned
     * @param storage Storage containing the storage-format String representation of a TaskList to be parsed.
     * @return TaskList of Tasks extracted from the given Storage.
     * @throws DukeException If there are invalid tokens in the Storage that cannot be converted to Tasks or
     *                       adding the Tasks into the TaskList is unsuccessful.
     */
    public static TaskList parse(Storage storage) throws DukeException {
        try {
            File file = new File(storage.getFilePath());
            Scanner sc = new Scanner(file);
            TaskList taskList = new TaskList();
            while (sc.hasNextLine()) {
                String[] arr = sc.nextLine().split(" \\| ");
                String type = LoadValidation.getValidatedTaskType(arr[0].trim());
                assert type.equals("T") || type.equals("D") || type.equals("E")
                        : "Exception meant to be thrown for wrong Task type "
                        + "but exception was not thrown.";
                LoadValidation.ensureValidNumberOfTokens(type, arr);
                assert arr.length == 3 || arr.length == 4 : "Exception meant to be thrown for invalid number of tokens "
                        + "but exception was not thrown.";
                boolean isDone = LoadValidation.getValidatedDoneStatus(arr[1].trim()) == 1 ? true : false;
                String taskString = arr[2].trim();
                DateAndTime dateAndTime = null;
                Validation.ensureNonEmptyTaskString(type, taskString);
                assert !taskString.equals("") : "Task Description is empty in Parser but no exception was thrown.";
                if (type.equals("E")) {
                    dateAndTime = EventValidation.getValidatedDateAndTime(arr[3]);
                    assert dateAndTime != null : "DateAndTime of Task parsed in Parser is invalid "
                            + "but no exception was thrown.";
                    taskList.add(new Event(taskString, dateAndTime, isDone));
                } else if (type.equals("D")) {
                    dateAndTime = DeadlineValidation.getValidatedDateAndTime(arr[3]);
                    assert dateAndTime != null : "DateAndTime of Task parsed in Parser is invalid "
                            + "but no exception was thrown.";
                    taskList.add(new Deadline(taskString, dateAndTime, isDone));
                } else {
                    taskList.add(new Todo(taskString, isDone));
                }
            }
            return taskList;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new FileNotFoundException("We cannot find your file.");
        }
    }
}
