package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeadlineCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DoneCommand;
import seedu.duke.commands.EventCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.SortCommand;
import seedu.duke.commands.TodoCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.UnknownCommandException;

/**
 * Parses string commands.
 */
public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String SORT_COMMAND = "sort";

    private TaskList taskList;

    Parser(Storage storage) {
        taskList = new TaskList(storage);
        taskList.addAll(storage.load());
    }

    /**
     * Parse an input to a {@code Command}.
     * @param input The user input
     * @return The command.
     */
    Command parse(String input) throws DukeException {
        input = input.trim();
        if (input.length() == 0) {
            return null;
        }
        String[] strs = input.split(" ", 2);
        String command = strs[0];
        String arg = "";
        if (strs.length > 1) { // there are more words
            arg = strs[1].trim();
        }
        switch (command) {
        case TODO_COMMAND:
            return new TodoCommand(arg, taskList);
        case DEADLINE_COMMAND:
            return new DeadlineCommand(arg, taskList);
        case EVENT_COMMAND:
            return new EventCommand(arg, taskList);
        case BYE_COMMAND:
            return new ByeCommand();
        case LIST_COMMAND:
            return new ListCommand(taskList);
        case DONE_COMMAND:
            return new DoneCommand(arg, taskList);
        case DELETE_COMMAND:
            return new DeleteCommand(arg, taskList);
        case FIND_COMMAND:
            return new FindCommand(arg, taskList);
        case SORT_COMMAND:
            return new SortCommand(arg, taskList);
        default:
            throw new UnknownCommandException();
        }
    }
}
