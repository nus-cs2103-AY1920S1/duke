package duke.command;

import duke.task.ToDo;

public class ToDoCommand extends AddCommand {

    public static final String COMMAND_WORD = "todo";

    public ToDoCommand(String description) {
        super(new ToDo(description));
    }
}
