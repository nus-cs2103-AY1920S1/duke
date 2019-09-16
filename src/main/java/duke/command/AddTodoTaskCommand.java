package duke.command;

import duke.model.Task;
import duke.model.Todo;

public class AddTodoTaskCommand extends AddCommand {
    public AddTodoTaskCommand(String command) {
        super(command);
    }

    @Override
    protected Task instantiateTask() {
        String todoDescription = command;
        if (todoDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
        }
        return new Todo(todoDescription);
    }
}
