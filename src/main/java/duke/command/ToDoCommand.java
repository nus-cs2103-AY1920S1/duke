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

    /**
     * Returns a string of help information for the todo command.
     * @return a string of help information for the todo command.
     */
    public String getCommandHelpInfo() {
        return "The format of deadline command is: todo <taks description>";
    }
}
