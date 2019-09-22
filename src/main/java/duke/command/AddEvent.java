package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tag.Tag;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.util.HashSet;

/**
 * Represents a command to add an event object to TaskList.
 */
public class AddEvent extends Command {
    /** Description of event entered by user. */
    protected String userIn;

    private static final String MESSAGE_SUCCESS = "Got it. I've added this task: \n"
            + "%s" + "\n" + "Now you have %d task(s) in the list.";

    private static final String MESSAGE_INVALID_INPUT = "Invalid Input entered"
            + ":event (desc) /at (date&time) /tag (tags separated by spaces) -> tags are optional";

    /**
     * Initializes an Event object with the user input.
     * @param userIn description of event entered by user.
     */
    public AddEvent(String userIn) {
        this.userIn = userIn;
    }

    /**
     * Adds an event object into the list of tasks.
     * @param tasks TaskList object which represents the list of tasks created by the user.
     * @param storage storage which loads, saves and stores data of user.
     * @return CommandResult result of executing the command.
     * @throws DukeException if user input is invalid.
     */
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task newEvent;
            boolean isTagged = isTagged(userIn);

            if (isTagged) {
                newEvent = createTagged(userIn);
            } else {
                newEvent = createUntagged(userIn);
            }

            tasks.addTask(newEvent);
            int taskNum = tasks.getSize();
            storage.saveAll(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newEvent, taskNum));

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_INPUT);
        }
    }

    private boolean isTagged(String userIn) {
        return userIn.contains("/tag");
    }

    private String[] getTaggedArgs(String userIn) throws ArrayIndexOutOfBoundsException, DukeException {
        String[] args = new String[3];
        String[] splitBy = userIn.split("/at");
        String[] splitTag = splitBy[1].split("/tag");
        args[0] = splitBy[0].trim();
        args[1] = splitTag[0].trim();
        args[2] = splitTag[1].trim();

        if (checkEmpty(args)) {
            throw new DukeException(MESSAGE_INVALID_INPUT);
        }

        return args;
    }

    private String[] getUntaggedArgs(String userIn) throws ArrayIndexOutOfBoundsException, DukeException {
        String[] splitBy = userIn.split("/at");
        String[] args = new String[2];
        args[0] = splitBy[0].trim();
        args[1] = splitBy[1].trim();

        if (checkEmpty(args)) {
            throw new DukeException(MESSAGE_INVALID_INPUT);
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

    private Event createTagged(String userIn) throws DukeException {
        String[] args = getTaggedArgs(userIn);
        HashSet<Tag> tags = Parser.parseTags(args[2]);
        return new Event(args[0], args[1], tags);
    }

    private Event createUntagged(String userIn) throws DukeException {
        String[] args = getUntaggedArgs(userIn);
        return new Event(args[0], args[1]);
    }
}
