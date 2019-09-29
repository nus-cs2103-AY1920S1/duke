package duke.command;

import duke.task.Task;
import duke.task.Todo;

public class AddTodoCommand extends AddTaskCommand {

    public AddTodoCommand(String restOfCommand) {
        super(restOfCommand);
    }

    @Override
    public String getDescription() {
        return this.restOfCommand.strip();
    }

    @Override
    public String getDeadline() {
        return "no deadline";
    }

    @Override
    public Task createTask() {
        return new Todo(getDescription());
    }
}
