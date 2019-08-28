package duke.command;

import duke.task.ToDo;

/**
 * Represents a {@link Command} to add a {@link ToDo}.
 */
public class ToDoCommand extends AddCommand {

    public static final String COMMAND_WORD = "todo";

    public ToDoCommand(String description) {
        super(new ToDo(description));
    }
}
