package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tag.Tag;
import duke.task.Task;
import duke.task.TaskList;

public class AddTagCommand extends Command {
    /** User input. */
    String userIn;

    private static final String MESSAGE_SUCCESS = "Tag has been added \n %s";

    private static final String MESSAGE_INVALID_FORMAT = "Invalid input: addtag (valid task num) (1 tag only)";

    private static final String MESSAGE_INVALID_NUM_TAG = "One tag only";

    /**
     * Initializes an instance of AddTagCommand with user input.
     * @param userIn user input.
     */
    public AddTagCommand(String userIn) {
        this.userIn = userIn.trim();
    }

    /**
     * Adds a tag to specified task by user.
     * @param tasks TaskList object which represents the list of tasks created by the user.
     * @param storage storage which loads, saves and stores data of user.
     * @return CommandResult result of executing the command.
     * @throws DukeException if user input is invalid.
     */
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] args = getArgs(userIn);
            boolean isOneWordTag = isOneWord(args[1]);

            if (isOneWordTag) {
                Task t = addTag(tasks, args);
                storage.saveAll(tasks);
                return new CommandResult(String.format(MESSAGE_SUCCESS, t));
            } else {
                throw new DukeException(MESSAGE_INVALID_NUM_TAG);
            }

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_FORMAT);
        }
    }

    private boolean isOneWord(String tags) {
        return !tags.contains(" ");
    }

    private String[] getArgs(String userIn) throws IndexOutOfBoundsException, DukeException {
        String[] args = new String[2];
        String[] splitInput = userIn.split(" ", 2);
        args[0] = splitInput[0].trim();
        args[1] = splitInput[1].trim();

        if (checkEmpty(args)) {
            throw new DukeException(MESSAGE_INVALID_FORMAT);
        }

        return args;
    }

    /**
     * Checks if arguments are whitespaces.
     * @param args args to be checked.
     * @return if any argument is whitespace.
     */
    private boolean checkEmpty(String[] args) {
        for (String a : args) {
            if (a.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Task addTag(TaskList tasks, String[] args) throws DukeException, NumberFormatException {
        Tag tag = new Tag(args[1]);
        int taskInd = Integer.parseInt(args[0]) - 1;
        Task t = tasks.getTask(taskInd);
        tasks.alterTaskTag(taskInd, tag, true);
        return t;
    }
}
