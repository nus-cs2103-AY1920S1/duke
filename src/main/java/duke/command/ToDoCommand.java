package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.task.ToDo;

/**
 * A class representing add a todo task command.
 */
public class ToDoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";

    /**
     * Class constructor specifying the task description.
     * @param description the task description.
     * @throws IllegalDescriptionException If the description is illegal.
     */
    public ToDoCommand(String description) throws IllegalDescriptionException {
        super(new ToDo(description));
    }
}
