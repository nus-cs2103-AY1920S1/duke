package duke.parser;

import duke.exception.DukeException;

import duke.command.AddDeadline;
import duke.command.AddEvent;
import duke.command.AddTagCommand;
import duke.command.AddTodo;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DeleteTagCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.FindTagCommand;
import duke.command.ListCommand;
import duke.command.ListTagCommand;

import duke.tag.Tag;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents a Parser which is used to convert user input into the necessary objects.
 */
public class Parser {
    private static final String MESSAGE_INVALID_INPUT = "Invalid Input entered";
    private static final String MESSAGE_INVALID_TAG = "Invalid Tag entered";

    /**
     * Converts commands from user into the corresponding command objects to execute the commands.
     * @param command command from the user.
     * @return Command object created from the command from user.
     * @throws DukeException if command is invalid.
     */
    public static Command parseCommand(String command) throws DukeException {
        try {
            String[] args = getArgs(command.trim());
            String commandWord = args[0];
            boolean isOneWord = isOneWord(command.trim());

            if (isOneWord) {
                switch (commandWord.toLowerCase()) {
                case "list":
                    return new ListCommand();
                case "bye":
                    return new ExitCommand();
                case "listtag":
                    return new ListTagCommand();
                default:
                    throw new DukeException(MESSAGE_INVALID_INPUT);
                }

            } else {
                switch (commandWord.toLowerCase()) {
                case "todo":
                    return new AddTodo(args[1]);
                case "deadline":
                    return new AddDeadline(args[1]);
                case "event":
                    return new AddEvent(args[1]);
                case "done":
                    return new DoneCommand(args[1]);
                case "delete":
                    return new DeleteCommand(args[1]);
                case "find":
                    return new FindCommand(args[1]);
                case "findtag":
                    return new FindTagCommand(args[1]);
                case "addtag":
                    return new AddTagCommand(args[1]);
                case "deltag":
                    return new DeleteTagCommand(args[1]);
                default:
                    throw new DukeException(MESSAGE_INVALID_INPUT);
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_INPUT);
        }
    }

    private static String[] getArgs(String command) {
        return command.split(" ", 2);
    }

    private static boolean isOneWord(String command) {
        return command.split(" ").length == 1;
    }

    /**
     * Creates new Task objects depending on the saved data of the user
     * and adds it to the list of tasks. Completion of tasks are also checked
     * against the data and will be marked as done if the task is completed.
     * @param loadData saved data from the user to be loaded.
     * @param tasks ArrayList of tasks created from loading previously saved data.
     * @throws DukeException if description or time and date of tasks are invalid.
     */
    public static void parseLoad(String loadData, ArrayList<Task> tasks) throws DukeException {
        String[] args = loadData.split(" \0 ");
        String taskType = args[0];
        Task t;

        switch (taskType) {
        case "T":
            t = createTodo(args);
            break;
        case "D":
            t = createDeadline(args);
            break;
        default:
            assert false;
            t = createEvent(args);
        }

        assert t != null;
        tasks.add(t);
    }

    private static Task createTodo(String[] args) throws DukeException {
        String desc = args[2];
        boolean isDone = args[1].equals("1");
        boolean isTagged = args.length == 4;
        Task t;

        if (isTagged) {
            String tags = args[3];
            t = new Todo(desc, parseTags(tags));
        } else {
            t = new Todo(desc);
        }

        if (isDone) {
            t.markAsDone();
        }

        return t;
    }

    private static Task createDeadline(String[] args) throws DukeException {
        String desc = args[2];
        boolean isDone = args[1].equals("1");
        boolean isTagged = args.length == 5;
        String by = args[3];
        Task t;

        if (isTagged) {
            String tags = args[4];
            t = new Deadline(desc, by, parseTags(tags));
        } else {
            t = new Deadline(desc, by);
        }

        if (isDone) {
            t.markAsDone();
        }

        return t;
    }

    private static Task createEvent(String[] args) throws DukeException {
        String desc = args[2];
        boolean isDone = args[1].equals("1");
        boolean isTagged = args.length == 5;
        String at = args[3];
        Task t;

        if (isTagged) {
            String tags = args[4];
            t = new Event(desc, at, parseTags(tags));
        } else {
            t = new Event(desc, at);
        }

        if (isDone) {
            t.markAsDone();
        }

        return t;
    }

    /**
     * Puts all the tags specified in a string in a hashset.
     * @param t string of tags.
     * @return hashset of tag objects.
     */
    public static HashSet<Tag> parseTags(String t) throws DukeException {
        boolean isWhiteSpace = t.trim().isEmpty();
        if (isWhiteSpace) {
            throw new DukeException(MESSAGE_INVALID_TAG);
        }
        HashSet<Tag> tags = new HashSet<Tag>();
        String[] splitTags = t.split(" ");
        Arrays.stream(splitTags).map(Tag::new).forEach(tags::add);
        return tags;
    }

}
