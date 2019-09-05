package duke.command;

import duke.task.ToDo;

import static java.lang.System.lineSeparator;

/**
 * Represents a {@link Command} to add a {@link ToDo}.
 */
public class ToDoCommand extends AddCommand {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Create a new task" + lineSeparator()
            + "Usage: " + COMMAND_WORD + " <description>";

    public ToDoCommand(String description) {
        super(new ToDo(description));
    }
}
