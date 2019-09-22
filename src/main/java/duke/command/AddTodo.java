package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tag.Tag;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.util.HashSet;

/**
 * Represents a command to add a Todo object to TaskList.
 */
public class AddTodo extends Command {
    /** Description of task entered by user.*/
    protected String userIn;

    private static final String MESSAGE_SUCCESS = "Got it. I've added this task: \n"
            + "%s" + "\n" + "Now you have %d task(s) in the list.";

    private static final String MESSAGE_INVALID_INPUT = "Invalid description entered"
            + "todo (desc) /tag (tags separated by space) -> tags are optional";

    /**
     * Initializes an AddTodo Object with the user input.
     * @param userIn description of task entered by user.
     */
    public AddTodo(String userIn) {
        this.userIn = userIn;
    }

    /**
     * Adds a Todo task object into the list of tasks.
     * @param tasks TaskList object which represents the list of tasks created by the user.
     * @param storage storage which loads, saves and stores data of user.
     * @return CommandResult result of executing the command.
     * @throws DukeException if user input is invalid.
     */
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task newTodo;
            boolean isTagged = isTagged(userIn);

            if (isTagged) {
                newTodo = createTagged(userIn);
            } else {
                newTodo = createUntagged(userIn);
            }

            tasks.addTask(newTodo);
            int taskNum = tasks.getSize();
            storage.saveAll(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newTodo, taskNum));

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_INPUT);
        }
    }

    private boolean isTagged(String userIn) {
        return userIn.contains("/tag");
    }

    private String[] getTaggedArgs(String userIn) throws ArrayIndexOutOfBoundsException, DukeException {
        String[] args = new String[2];
        String[] splitTag = userIn.split("/tag");
        args[0] = splitTag[0].trim();
        args[1] = splitTag[1].trim();

        if (checkEmpty(args)) {
            throw new DukeException(MESSAGE_INVALID_INPUT);
        }

        return args;
    }

    private String[] getUntaggedArgs(String userIn) throws DukeException {
        String[] args = new String[1];
        args[0] = userIn.trim();

        if (checkEmpty(args)) {
            throw new DukeException(MESSAGE_INVALID_INPUT);
        }

        return args;
    }

    /**
     * Checks if arguments are empty strings.
     * @param args args to be checked.
     * @return if any argument is empty string.
     */
    private boolean checkEmpty(String[] args) {
        for (String a : args) {
            if (a.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Todo createTagged(String userIn) throws DukeException {
        String[] args = getTaggedArgs(userIn);
        HashSet<Tag> tags = Parser.parseTags(args[1]);
        return new Todo(args[0], tags);
    }

    private Todo createUntagged(String userIn) throws DukeException {
        String[] args = getUntaggedArgs(userIn);
        return new Todo(args[0]);
    }
}
