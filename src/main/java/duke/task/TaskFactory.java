package duke.task;

import duke.io.DateTime;
import duke.io.Parser;

import duke.exception.IllegalInstructionException;
import duke.exception.DukeException;
import duke.exception.IllegalDateException;

/**
 * The TaskFactory class should be used to create all implementing classes of the abstract base
 * class Task. The class provides the logic to handle the type of task to be returned to the
 * caller.
 */
public class TaskFactory {
    /**
     * Returns a new Task object given the command.
     *
     * @param command user input command
     * @return a new Task object
     * @throws IllegalInstructionException if the command is not of a valid form
     */
    public static Task createTask(String command) throws IllegalInstructionException {
        switch (command.split("\\s+")[0]) {
        case Parser.COMMAND_TODO:
            return createTodoTask(command);
        case Parser.COMMAND_DEADLINE:
            return createDeadlineTask(command);
        case Parser.COMMAND_EVENT:
            return createEventTask(command);
        default:
            throw new IllegalInstructionException("String is in invalid form.");
        }
    }

    private static Task createTodoTask(String command) {
        int indexToChop = Parser.COMMAND_TODO.length();
        String description = command.substring(indexToChop).trim();
        return TaskTodo.of(description);
    }

    private static Task createDeadlineTask(String command) throws IllegalInstructionException {
        try {
            int indexToChop = Parser.COMMAND_DEADLINE.length();
            String[] deadline = command.split("/by");
            String description = deadline[0].substring(indexToChop).trim();
            DateTime by;
            by = DateTime.ofDate(deadline[1].trim());
            return TaskDeadline.of(description, by);
        } catch (IllegalDateException e) {
            throw new IllegalInstructionException(String.format("%s", DateTime.EXPECTED_FORMAT_WARNING));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalInstructionException("String is not in valid form!");
        }
    }

    private static Task createEventTask(String command) throws IllegalInstructionException {
        try {
            int indexToChop = Parser.COMMAND_EVENT.length();
            String[] event = command.split("/at");
            String description = event[0].substring(indexToChop).trim();
            DateTime at;
            at = DateTime.ofDate(event[1].trim());
            return TaskEvent.of(description, at);
        } catch (IllegalDateException e) {
            throw new IllegalInstructionException(String.format("%s", DateTime.EXPECTED_FORMAT_WARNING));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalInstructionException("String is not in valid form!");
        }
    }

    /**
     * Returns a new Task corresponding to its fileFormattedString.
     *
     * @param fileFormattedString of the task
     * @return a new Task
     * @throws DukeException if the string is not in the correct format
     */
    static Task createTaskFromFileFormattedString(String fileFormattedString)
            throws DukeException {
        String type = fileFormattedString.split("\\s+\\|\\s+")[0];
        switch (type) {
        case "T":
            return TaskTodo.ofFileFormattedForm(fileFormattedString);
        case "D":
            return TaskDeadline.ofFileFormattedForm(fileFormattedString);
        case "E":
            return TaskEvent.ofFileFormattedForm(fileFormattedString);
        default:
            throw new DukeException("String is in invalid form.");
        }
    }
}
