package duke.directprocessor;

import duke.KeyWord;
import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FakeCommand;
import duke.commands.FindCommand;
import duke.commands.FinishCommand;
import duke.commands.ListCommand;
import duke.DukeException;
import duke.tasks.TaskType;

/**
 * This is a class that recognizes the user's input and calls the corresponding command.
 * It only recognizes the first word of the user input and key words like "/at", "/by".
 * All of its methods are static, so there is no necessity to initialize one.
 */
public class Parser {

    /**
     * It recognizes the user input and call the corresponding commands.
     *
     * @param s The user's input as a string.
     * @return The recognized command.
     * @throws DukeException If the command is incomplete.
     */
    public static Command parse(String s) throws DukeException {
        String[] splitInput = splitInput(s);
        KeyWord keyWord = KeyWord.valueOf(splitInput[0].toUpperCase());
        switch (keyWord) {
            case LIST: return listCommand();
            case BYE: return exitCommand();
            case TODO: return todoCommand(splitInput);
            case EVENT: return eventCommand(splitInput);
            case DEADLINE: return deadlineCommand(splitInput);
            case DONE: return finishCommand(splitInput);
            case DELETE: return deleteCommand(splitInput);
            case FIND: return findCommand(splitInput);
            default: return fakeCommand();
        }
    }

    /**
     * The assistance method to return a list command.
     *
     * @return A new ListCommand.
     */
    private static Command listCommand() {
        return new ListCommand();
    }

    /**
     * The assistance method to return an exit command.
     *
     * @return A new ExitCommand.
     */
    private static Command exitCommand() {
        return new ExitCommand();
    }

    /**
     * The assistance method to return a todo command.
     *
     * @param splitInput The user input split by white space.
     * @return A new TodoCommand.
     */
    private static Command todoCommand(String[] splitInput) {
        String taskName = "";
        for (int i = 1; i < splitInput.length; i++) {
            taskName = taskName + splitInput[i];
            if (i != splitInput.length - 1) {
                taskName = taskName + " ";
            }
        }
        return new AddCommand(TaskType.T, taskName);
    }

    /**
     * The assistance method to return a event command.
     *
     * @param splitInput The user input split by white space.
     * @return A new EventCommand.
     */
    private static Command eventCommand(String[] splitInput) {
        String taskName = "";
        String taskTime = "";
        int timeStartFrom = 0;
        for (int i = 1; i < splitInput.length; i++) {
            if (splitInput[i].equals("/at")) {
                timeStartFrom = i + 1;
                break;
            }
            if (i != 1) {
                taskName = taskName + " ";
            }
            taskName = taskName + splitInput[i];
        }
        if (timeStartFrom != 0) {
            for (int i = timeStartFrom; i < splitInput.length; i++) {
                taskTime = taskTime + splitInput[i];
                if (i != splitInput.length - 1) {
                    taskTime = taskTime + " ";
                }
            }
        }
        return new AddCommand(TaskType.E, taskName, taskTime);
    }

    /**
     * The assistance method to return a deadline command.
     *
     * @param splitInput The user input split by white space.
     * @return A new DeadlineCommand.
     */
    private static Command deadlineCommand(String[] splitInput) {
        String taskName = "";
        String taskTime = "";
        int timeStartFrom = 0;
        for (int i = 1; i < splitInput.length; i++) {
            if (splitInput[i].equals("/by")) {
                timeStartFrom = i + 1;
                break;
            }
            if (i != 1) {
                taskName = taskName + " ";
            }
            taskName = taskName + splitInput[i];
        }
        if (timeStartFrom != 0) {
            for (int i = timeStartFrom; i < splitInput.length; i++) {
                taskTime = taskTime + splitInput[i];
                if (i != splitInput.length - 1) {
                    taskTime = taskTime + " ";
                }
            }
        }
        return new AddCommand(TaskType.D, taskName, taskTime);
    }

    /**
     * The assistance method to return a finish command.
     *
     * @param splitInput The user input split by white space.
     * @return A new FinishCommand.
     * @throws DukeException If the index of the finished task is not specified.
     */
    private static Command finishCommand(String[] splitInput) throws DukeException{
        try {
            return new FinishCommand(Integer.parseInt(splitInput[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify which task is finished by its order in the task list.");
        }
    }

    /**
     * The assistance method to return a delete command.
     *
     * @param splitInput The user input split by white space.
     * @return A new DeleteCommand.
     * @throws DukeException if the index of the task to delete is not specified.
     */
    private static Command deleteCommand(String[] splitInput) throws DukeException{
        try {
            return new DeleteCommand(Integer.parseInt(splitInput[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Plase specify which task needs to be deleted by its order in the task list.");
        }
    }

    /**
     * The assistance method to return a find command.
     *
     * @param splitInput The user input split by white space.
     * @return A new FindCommand.
     */
    private static Command findCommand(String[] splitInput) {
        String target = "";
        for (int i = 1; i < splitInput.length; i++) {
            target = target + splitInput[i];
            if (i != splitInput.length - 1) {
                target = target + " ";
            }
        }
        return new FindCommand(target);
    }

    /**
     * The assistance method to return a fake command.
     *
     * @return A new FakeCommand.
     */
    private static Command fakeCommand() {
        return new FakeCommand();
    }

    /**
     * This method helps breaks the user's input by white spaces.
     *
     * @param s The user input as a string.
     * @return The split user input.
     */
    private static String[] splitInput(String s) {
        return s.split(" ");
    }
}
